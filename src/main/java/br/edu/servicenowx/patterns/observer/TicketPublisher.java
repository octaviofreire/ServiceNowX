package br.edu.servicenowx.patterns.observer;
import java.util.List;
import java.util.ArrayList;
public class TicketPublisher {

    private final List<TicketObserver> observers = new ArrayList<>();
    
    public void subscribe(TicketObserver observer){
        observers.add(observer);
    }

    public void publish(String id,String event){
        for (TicketObserver observer : observers) {
            observer.update(id,event);
        }
    }
}
