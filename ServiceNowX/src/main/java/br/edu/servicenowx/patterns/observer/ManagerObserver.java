package br.edu.servicenowx.patterns.observer;
public class ManagerObserver implements TicketObserver {
    public void update(String id,String event){System.out.println("MANAGER "+id+" "+event);}
}
