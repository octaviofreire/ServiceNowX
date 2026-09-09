package br.edu.servicenowx.model;
import java.util.*;
public class Ticket {
    public String id;
    public String requester;
    public String category;
    public String priority;
    public String status="OPEN";
    public String technicianId;
    public String openedAt;
    public String dueAt;
    public List<String> history=new ArrayList<>();
    public Ticket(String id,String requester,String category,String priority,String openedAt){
        this.id=id;this.requester=requester;this.category=category;this.priority=priority;this.openedAt=openedAt;
    }
}
