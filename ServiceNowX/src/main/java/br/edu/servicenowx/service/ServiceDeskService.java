package br.edu.servicenowx.service;

import br.edu.servicenowx.model.*;
import br.edu.servicenowx.repository.*;
import br.edu.servicenowx.legacy.*;
import br.edu.servicenowx.patterns.strategy.*;
import br.edu.servicenowx.patterns.observer.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ServiceDeskService {
    public final InMemoryRepository<Ticket> tickets = new InMemoryRepository<>();
    public final InMemoryRepository<Technician> technicians = new InMemoryRepository<>();

    private final DirectoryLegacyApi directory = new DirectoryLegacyApi();
    private final MonitoringLegacyApi monitoring = new MonitoringLegacyApi();
    private final NotificationLegacyApi notifications = new NotificationLegacyApi();
    private final VendorSupportLegacyApi vendors = new VendorSupportLegacyApi();
    private final AssignmentService assignment = new AssignmentService();
    private final TicketPublisher publisher = new TicketPublisher();

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ServiceDeskService() {
        publisher.subscribe(new RequesterObserver());
        publisher.subscribe(new ManagerObserver());
    }

    public Ticket openTicket(String id, String requester, String category,
                             String priority, String openedAt) {
        String user = directory.findUser(requester);
        System.out.println("DIRECTORY=" + user);

        Ticket t = new Ticket(id, requester, category, priority, openedAt);
        t.dueAt = calculateDue(priority, openedAt);
        tickets.save(id, t);
        publisher.publish(id, "TICKET_OPENED");
        return t;
    }

    /**
     * Calcula o vencimento do SLA a partir do instante real de abertura.
     * Regras atuais do domínio: CRITICAL=2h, HIGH=12h, demais=24h.
     */
    public String calculateDue(String priority, String openedAt) {
        if (openedAt == null || openedAt.isBlank()) {
            throw new IllegalArgumentException("openedAt é obrigatório");
        }

        int slaHours = switch (priority == null ? "" : priority.toUpperCase()) {
            case "CRITICAL" -> 2;
            case "HIGH" -> 12;
            default -> 24;
        };

        return LocalDateTime.parse(openedAt, DATE_FORMAT)
                .plusHours(slaHours)
                .format(DATE_FORMAT);
    }

    public void assign(String ticketId) {
        Ticket t = tickets.find(ticketId);
        if (t == null) return;

        Technician tech = assignment.assign(t, technicians.all());
        if (tech != null) {
            t.technicianId = tech.id;
            tech.activeTickets++;
            tech.available = false;
            t.status = "ASSIGNED";
            publisher.publish(ticketId, "TICKET_ASSIGNED");
        }
    }

    public void resolve(String ticketId) {
        Ticket t = tickets.find(ticketId);
        if (t == null) return;

        if (t.technicianId == null || t.technicianId.isBlank()) {
            throw new IllegalStateException(
                    "Chamado não pode ser resolvido sem técnico atribuído");
        }

        Technician tech = technicians.find(t.technicianId);
        if (tech != null) {
            if (tech.activeTickets > 0) {
                tech.activeTickets--;
            }
            tech.available = true;
        }

        t.status = "RESOLVED";
        notifications.send("EMAIL", "user@exemplo.com", "Chamado " + ticketId + " resolvido");
        publisher.publish(ticketId, "TICKET_RESOLVED");
    }

    public void escalate(String ticketId) {
        Ticket t = tickets.find(ticketId);
        if (t == null) return;
        vendors.openCase("VENDOR-X", "Ticket " + ticketId + " " + t.category);
        t.status = "ESCALATED";
        publisher.publish(ticketId, "TICKET_ESCALATED");
    }

    public Ticket ingestMonitoringAlert(String source, String message) {
        String raw = monitoring.createIncident(source, message);
        String id = "MON-" + source;
        Ticket t = new Ticket(id, "monitoring", source, "CRITICAL","2026-08-10T23:00:00");
        t.history.add(raw);
        tickets.save(id, t);
        return t;
    }
}
