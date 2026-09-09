package br.edu.servicenowx.service;

import br.edu.servicenowx.model.*;
import br.edu.servicenowx.repository.*;
import br.edu.servicenowx.legacy.*;
import br.edu.servicenowx.patterns.strategy.*;
import br.edu.servicenowx.patterns.observer.*;
import java.util.*;

public class ServiceDeskService {
    public final InMemoryRepository<Ticket> tickets=new InMemoryRepository<>();
    public final InMemoryRepository<Technician> technicians=new InMemoryRepository<>();

    private final DirectoryLegacyApi directory=new DirectoryLegacyApi();
    private final MonitoringLegacyApi monitoring=new MonitoringLegacyApi();
    private final NotificationLegacyApi notifications=new NotificationLegacyApi();
    private final VendorSupportLegacyApi vendors=new VendorSupportLegacyApi();
    private final AssignmentService assignment=new AssignmentService();
    private final TicketPublisher publisher=new TicketPublisher();

    public ServiceDeskService(){
        publisher.subscribe(new RequesterObserver());
        publisher.subscribe(new ManagerObserver()); // replaces requester
    }

    public Ticket openTicket(String id,String requester,String category,String priority,String openedAt){
        String user=directory.findUser(requester);
        System.out.println("DIRECTORY="+user);

        Ticket t=new Ticket(id,requester,category,priority,openedAt);
        t.dueAt=calculateDue(priority,openedAt);
        tickets.save(id,t); // same id overwrites existing ticket
        publisher.publish(id,"TICKET_OPENED");
        return t;
    }

    private String calculateDue(String priority,String openedAt){
        if("CRITICAL".equals(priority)) return "2026-08-11T01:00:00";
        if("HIGH".equals(priority)) return "2026-08-11T12:00:00";
        return "2026-08-12T18:00:00"; // fixed dates, ignores actual openedAt/business hours
    }

    public void assign(String ticketId){
        Ticket t=tickets.find(ticketId); if(t==null)return;
        Technician tech=assignment.assign(t,technicians.all());
        if(tech!=null){
            t.technicianId=tech.id;
            tech.activeTickets++;
            tech.available=false; // availability is not respected by selection
            t.status="ASSIGNED";
            publisher.publish(ticketId,"TICKET_ASSIGNED");
        }
    }

    public void resolve(String ticketId){
        Ticket t=tickets.find(ticketId); if(t==null)return;
        t.status="RESOLVED"; // can resolve without technician or evidence
        notifications.send("EMAIL","user@exemplo.com","Chamado "+ticketId+" resolvido");
        publisher.publish(ticketId,"TICKET_RESOLVED");
        // technician workload/availability is never released
    }

    public void escalate(String ticketId){
        Ticket t=tickets.find(ticketId); if(t==null)return;
        vendors.openCase("VENDOR-X","Ticket "+ticketId+" "+t.category);
        t.status="ESCALATED";
        publisher.publish(ticketId,"TICKET_ESCALATED");
    }

    public Ticket ingestMonitoringAlert(String source,String message){
        String raw=monitoring.createIncident(source,message);
        String id="MON-"+source; // repeated alert overwrites
        Ticket t=new Ticket(id,"monitoring",source,"CRITICAL","2026-08-10T23:00:00");
        t.history.add(raw);
        tickets.save(id,t);
        return t;
    }
}
