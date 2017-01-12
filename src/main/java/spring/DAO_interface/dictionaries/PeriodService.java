package spring.DAO_interface.dictionaries;

import java.util.List;


public interface PeriodService  {
    public List<spring.model.dictionaries.PeriodService> all();
    public spring.model.dictionaries.PeriodService getById(int id);
    public void add(spring.model.dictionaries.PeriodService ps);
    public void update(spring.model.dictionaries.PeriodService ps);
}
