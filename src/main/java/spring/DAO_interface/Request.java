package spring.DAO_interface;

import org.springframework.stereotype.Service;
import spring.model.ObjectIncas;

import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 02.01.2017.
 */

public interface Request {
    public List<spring.model.Request> all();
    public spring.model.Request get(int id);
    public void add(spring.model.Request r);
    public void delete(int id);
    public Set<ObjectIncas> getObjects(int id);
}
