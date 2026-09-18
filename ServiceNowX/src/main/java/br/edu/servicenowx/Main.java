package br.edu.servicenowx;
import br.edu.servicenowx.model.*;
import br.edu.servicenowx.service.*;
import br.edu.servicenowx.patterns.facade.*;
import br.edu.servicenowx.patterns.adapter.*;

public class Main {
    public static void main(String[] args){
        ServiceDeskService service=new ServiceDeskService();
        service.technicians.save("T1",new Technician("T1","Técnico 1","NETWORK"));

        service.openTicket("INC001","usuario1","NETWORK","HIGH","2026-08-10T20:00:00");
        service.openTicket("INC002","usuario2","NETWORK","CRITICAL","2026-08-10T20:05:00");

        service.assign("INC001");
        service.assign("INC002"); // same unavailable technician can be selected again

        ServiceNowXFacade facade=new ServiceNowXFacade(service,new DirectoryAdapter(),new VendorSupportAdapter());
        facade.resolve("INC001");
        facade.getService().escalate("INC002");
        facade.getService().ingestMonitoringAlert("SERVER01","CPU 99%");
        facade.getService().ingestMonitoringAlert("SERVER01","CPU 100%"); // overwrites previous alert

        System.out.println("INC001="+facade.getService().tickets.find("INC001").status);
        System.out.println("INC002_TECH="+facade.getService().tickets.find("INC002").technicianId);
        System.out.println("TECH_ACTIVE="+facade.getService().technicians.find("T1").activeTickets);
    }
}
