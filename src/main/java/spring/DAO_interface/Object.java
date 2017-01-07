package spring.DAO_interface;

import spring.model.ObjectIncas;

import java.util.List;

/**
 * Created by Саша on 02.01.2017.
 */
public interface Object {
    public List<ObjectIncas> all();
    public ObjectIncas get(int id);
    public void add(ObjectIncas o);
    public void delete(int id);
}
