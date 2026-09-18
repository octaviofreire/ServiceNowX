package br.edu.servicenowx.legacy;
public class MonitoringLegacyApi {
    public String createIncident(String source,String message){
        return source+";"+message+";CREATED";
    }
}
