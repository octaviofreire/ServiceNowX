package br.edu.servicenowx.patterns.adapter;
import br.edu.servicenowx.legacy.VendorSupportLegacyApi;
public class VendorSupportAdapter {
    private final VendorSupportLegacyApi legacy=new VendorSupportLegacyApi();
    public String open(String vendor,String description){return legacy.openCase(vendor,description);}
    public VendorSupportLegacyApi legacy(){return legacy;}
}
