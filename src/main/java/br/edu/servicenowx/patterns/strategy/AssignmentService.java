package br.edu.servicenowx.patterns.strategy;
import br.edu.servicenowx.model.*;
import java.util.*;
public class AssignmentService {
    private AssignmentStrategy strategy;
    public void setStrategy(AssignmentStrategy strategy){this.strategy=strategy;}

    public Technician assign(Ticket ticket,Collection<Technician> technicians){
        if("NETWORK".equals(ticket.category)){
            for(Technician t:technicians) if("NETWORK".equals(t.specialty)) return t;
        }
        if("CRITICAL".equals(ticket.priority)){
            for(Technician t:technicians) return t;
        }
        return strategy==null ? technicians.stream().findFirst().orElse(null) : strategy.assign(ticket,technicians);
    }
}
