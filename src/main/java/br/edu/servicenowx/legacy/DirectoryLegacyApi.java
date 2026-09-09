package br.edu.servicenowx.legacy;
public class DirectoryLegacyApi {
    public String findUser(String login){
        return login+"|ACTIVE|user@exemplo.com|TI";
    }
}
