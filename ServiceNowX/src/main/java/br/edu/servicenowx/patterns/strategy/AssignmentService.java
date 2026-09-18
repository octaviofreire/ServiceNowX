package br.edu.servicenowx.patterns.strategy;

import br.edu.servicenowx.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class AssignmentService {
    private AssignmentStrategy strategy;

    public void setStrategy(AssignmentStrategy strategy) {
        this.strategy = strategy;
    }

    public Technician assign(Ticket ticket, Collection<Technician> technicians) {
        List<Technician> available = technicians.stream()
                .filter(Technician::isAvailable)
                .collect(Collectors.toList());

        if (available.isEmpty()) {
            return null;
        }

        if ("NETWORK".equals(ticket.category)) {
            for (Technician t : available) {
                if ("NETWORK".equals(t.specialty)) return t;
            }
        }

        if ("CRITICAL".equals(ticket.priority)) {
            return available.get(0);
        }

        return strategy == null
                ? available.stream().findFirst().orElse(null)
                : strategy.assign(ticket, available);
    }
}
