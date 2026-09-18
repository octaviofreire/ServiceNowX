package br.edu.servicenowx.service;

import br.edu.servicenowx.model.Technician;
import br.edu.servicenowx.model.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDeskServiceTest {

    @Test
    void deveCalcularSlaAPartirDaAberturaReal() {
        ServiceDeskService service = new ServiceDeskService();

        String due = service.calculateDue("CRITICAL", "2026-09-09T19:00:00");

        assertEquals("2026-09-09T21:00:00", due);
    }

    @Test
    void deveIgnorarTecnicoIndisponivelNaAtribuicao() {
        ServiceDeskService service = new ServiceDeskService();
        Technician ocupado = new Technician("T1", "Carlos", "NETWORK");
        ocupado.available = false;
        Technician livre = new Technician("T2", "Ana", "NETWORK");

        service.technicians.save(ocupado.id, ocupado);
        service.technicians.save(livre.id, livre);

        Ticket ticket = service.openTicket(
                "INC-001", "joao", "NETWORK", "CRITICAL",
                "2026-09-09T19:00:00"
        );

        service.assign(ticket.id);

        assertEquals("T2", ticket.technicianId);
        assertFalse(livre.available);
        assertEquals(0, ocupado.activeTickets);
    }

    @Test
    void deveLiberarTecnicoAoResolverChamado() {
        ServiceDeskService service = new ServiceDeskService();
        Technician tech = new Technician("T1", "Carlos", "NETWORK");
        service.technicians.save(tech.id, tech);

        Ticket ticket = service.openTicket(
                "INC-002", "joao", "NETWORK", "HIGH",
                "2026-09-09T19:00:00"
        );

        service.assign(ticket.id);
        assertEquals(1, tech.activeTickets);
        assertFalse(tech.available);

        service.resolve(ticket.id);

        assertEquals("RESOLVED", ticket.status);
        assertEquals(0, tech.activeTickets);
        assertTrue(tech.available);
    }

    @Test
    void naoDeveResolverChamadoSemTecnico() {
        ServiceDeskService service = new ServiceDeskService();
        Ticket ticket = service.openTicket(
                "INC-003", "joao", "NETWORK", "HIGH",
                "2026-09-09T19:00:00"
        );

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.resolve(ticket.id)
        );

        assertTrue(exception.getMessage().contains("sem técnico atribuído"));
        assertEquals("OPEN", ticket.status);
    }
}
