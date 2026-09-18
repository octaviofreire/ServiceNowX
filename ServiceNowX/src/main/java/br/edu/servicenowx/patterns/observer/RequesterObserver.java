package br.edu.servicenowx.patterns.observer;
public class RequesterObserver implements TicketObserver {
    public void update(String id,String event){System.out.println("REQUESTER "+id+" "+event);}
}
