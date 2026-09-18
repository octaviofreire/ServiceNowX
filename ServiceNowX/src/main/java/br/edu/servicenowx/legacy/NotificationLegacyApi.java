package br.edu.servicenowx.legacy;
public class NotificationLegacyApi {
    public void send(String channel,String destination,String message){
        System.out.println(channel+" "+destination+" => "+message);
    }
}
