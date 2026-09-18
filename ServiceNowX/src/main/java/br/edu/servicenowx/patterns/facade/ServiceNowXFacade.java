package br.edu.servicenowx.patterns.facade;
import br.edu.servicenowx.service.ServiceDeskService;
import br.edu.servicenowx.patterns.adapter.*;
public class ServiceNowXFacade {
    public final ServiceDeskService service;
    public final DirectoryAdapter directory;
    public final VendorSupportAdapter vendor;
    public ServiceNowXFacade(ServiceDeskService s,DirectoryAdapter d,VendorSupportAdapter v){
        service=s;directory=d;vendor=v;
    }
    public void resolve(String ticketId){service.resolve(ticketId);}
    public ServiceDeskService getService(){return service;}
}
