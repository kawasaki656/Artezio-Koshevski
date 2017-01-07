package spring.DAO_interface;

import spring.model.ObjectIncas;

import java.util.Set;

/**
 * Created by Саша on 02.01.2017.
 */
public interface User {
    public void add(spring.model.User user);
    public Set<spring.model.Request> getRequests(String id_ses);
    public boolean updateSession(String login, String pass, String session);
    public boolean exitSession(String session);
}
