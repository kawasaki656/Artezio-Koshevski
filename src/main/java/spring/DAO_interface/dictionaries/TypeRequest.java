package spring.DAO_interface.dictionaries;

import java.util.List;


public interface TypeRequest {
    public List<spring.model.dictionaries.TypeRequest> all();
    public spring.model.dictionaries.TypeRequest getById(int id);
    public void add(spring.model.dictionaries.TypeRequest tr);
    public void update(spring.model.dictionaries.TypeRequest tr);
}
