package spring.controller;

import org.springframework.web.bind.annotation.*;
import spring.dao.dictionaries.IncasServiceDAO;
import spring.dao.dictionaries.PeriodServiceDAO;
import spring.dao.dictionaries.TypeRequestDAO;
import spring.dao.dictionaries.TypeSendMoneyDAO;
import spring.model.dictionaries.IncasService;
import spring.model.dictionaries.PeriodService;
import spring.model.dictionaries.TypeRequest;
import spring.model.dictionaries.TypeSendMoney;

import java.util.List;

/**
 * Created by Саша on 07.01.2017.
 */
@RestController
public class Dictionaries {
    private IncasServiceDAO services = new IncasServiceDAO();
    private TypeRequestDAO types = new TypeRequestDAO();
    private PeriodServiceDAO periods = new PeriodServiceDAO();
    private TypeSendMoneyDAO money = new TypeSendMoneyDAO();

    /* Services */
    @CrossOrigin
    @GetMapping("dictionaries/services")
    public List<IncasService> allServices(){
        return services.all();
    }

    @CrossOrigin
    @GetMapping("dictionaries/services/id/{id}")
    public IncasService getServiceById(@PathVariable int id){
        return services.getById(id);
    }

    @CrossOrigin
    @PutMapping("dictionaries/services")
    public void updateService(@RequestBody IncasService is){
        services.update(is);
    }

    @CrossOrigin
    @PostMapping("dictionaries/services")
    public void addService(@RequestBody IncasService is){
        services.add(is);
    }

    /* Type Requests */
    @CrossOrigin
    @GetMapping("dictionaries/typerequests")
    public List<TypeRequest> allTypes(){
        return types.all();
    }

    @CrossOrigin
    @PutMapping("dictionaries/typerequests")
    public void updateType(@RequestBody TypeRequest tr){
        types.update(tr);
    }

    @CrossOrigin
    @PostMapping("dictionaries/typerequests")
    public void addType(@RequestBody TypeRequest tr){
        types.add(tr);
    }

    /* Money */
    @CrossOrigin
    @GetMapping("dictionaries/money")
    public List<TypeSendMoney> allMoney(){
        return money.all();
    }

    @CrossOrigin
    @PutMapping("dictionaries/money")
    public void updateMoney(@RequestBody TypeSendMoney tsm){
        money.update(tsm);
    }

    @CrossOrigin
    @PostMapping("dictionaries/money")
    public void addMoney(@RequestBody TypeSendMoney tsm){
        money.add(tsm);
    }

    /* Period */
    @CrossOrigin
    @GetMapping("dictionaries/period")
    public List<PeriodService> allPeriods(){
        return periods.all();
    }

    @CrossOrigin
    @PutMapping("dictionaries/period")
    public void updatePeriod(@RequestBody PeriodService ps){
        periods.update(ps);
    }

    @CrossOrigin
    @PostMapping("dictionaries/period")
    public void addPeriod(@RequestBody PeriodService ps){
        periods.add(ps);
    }
}

