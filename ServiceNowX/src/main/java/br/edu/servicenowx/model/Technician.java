package br.edu.servicenowx.model;

public class Technician {
    public String id;
    public String name;
    public String specialty;
    public boolean available = true;
    public int activeTickets;

    public Technician(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public boolean isAvailable() {
        return available;
    }
}
