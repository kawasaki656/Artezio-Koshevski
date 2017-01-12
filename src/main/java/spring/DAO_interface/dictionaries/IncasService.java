package spring.DAO_interface.dictionaries;

import java.util.List;


public interface IncasService {
    public List<spring.model.dictionaries.IncasService> all();
    public spring.model.dictionaries.IncasService getById(int id);
    public void add(spring.model.dictionaries.IncasService is);
    public void update(spring.model.dictionaries.IncasService is);
}
