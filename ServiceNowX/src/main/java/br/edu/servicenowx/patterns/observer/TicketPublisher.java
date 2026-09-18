package br.edu.servicenowx.patterns.observer;
public class TicketPublisher {
    private TicketObserver observer;
    public void subscribe(TicketObserver observer){this.observer=observer;}
    public void publish(String id,String event){if(observer!=null)observer.update(id,event);}
}
