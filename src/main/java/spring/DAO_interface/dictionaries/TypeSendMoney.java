package spring.DAO_interface.dictionaries;

import java.util.List;


public interface TypeSendMoney {
    public List<spring.model.dictionaries.TypeSendMoney> all();
    public spring.model.dictionaries.TypeSendMoney getById(int id);
    public void add(spring.model.dictionaries.TypeSendMoney tsm);
    public void update(spring.model.dictionaries.TypeSendMoney tsm);
}
