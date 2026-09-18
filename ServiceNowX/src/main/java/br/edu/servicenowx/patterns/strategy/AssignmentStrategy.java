package br.edu.servicenowx.patterns.strategy;
import br.edu.servicenowx.model.*;
import java.util.Collection;
public interface AssignmentStrategy {
    Technician assign(Ticket ticket, Collection<Technician> technicians);
}
