package br.edu.servicenowx.patterns.abstractfactory;
import br.edu.servicenowx.legacy.*;
public class SupportIntegrationFactory {
    public Object directory(String family){return new DirectoryLegacyApi();}
    public Object monitoring(String family){return new MonitoringLegacyApi();}
    public Object vendor(String family){return new VendorSupportLegacyApi();}
}
