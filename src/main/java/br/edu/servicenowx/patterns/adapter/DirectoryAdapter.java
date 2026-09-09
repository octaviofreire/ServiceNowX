package br.edu.servicenowx.patterns.adapter;
import br.edu.servicenowx.legacy.DirectoryLegacyApi;
public class DirectoryAdapter extends DirectoryLegacyApi {
    public boolean isActive(String login){return findUser(login).contains("|ACTIVE|");}
    public String raw(String login){return findUser(login);}
}
