package br.edu.servicenowx.patterns.factory;
import br.edu.servicenowx.model.Ticket;
public class TicketFactory {
    public static Ticket create(String type,String id,String requester,String category,String priority,String openedAt){
        return new Ticket(id,requester,category,priority,openedAt);
    }
}
