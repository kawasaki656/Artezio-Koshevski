package spring.controller;

import java.util.*;

import org.springframework.http.HttpHeaders;
import spring.dao.ObjectDAO;
import spring.dao.RequestDAO;
import spring.dao.UserDAO;
import spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.model.ObjectIncas;
import spring.model.Request;
import spring.requests.*;

import java.text.SimpleDateFormat;

@RestController
public class Requests {

    private UserDAO users = new UserDAO();
    private RequestDAO reqs = new RequestDAO();
    private ObjectDAO objs = new ObjectDAO();
    @CrossOrigin
    @GetMapping("requests/session/{session}")
    public ResponseEntity getUserRequests(@PathVariable("session") String session){
        try {
            if(session.equals("0"))
                throw new NoSuchElementException();
            Set<Request> userRequests = users.getRequests(session);
            Set<Request> newUserRequests = new HashSet<Request>();
            for (Request tmp : userRequests) {
                tmp.setObjectIncases(null);
                tmp.setU(null);
                newUserRequests.add(tmp);
            }
            return new ResponseEntity(newUserRequests,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            HttpHeaders head = new HttpHeaders();
            head.add("Content-type", "text/html;charset=UTF-8");
            return new ResponseEntity("Не воруйте чужие данные !",head,HttpStatus.OK);
        }
    }

    @CrossOrigin
    @GetMapping("requests/objects/id/{id}")
    public Set<ObjectIncas> getObjectsById(@PathVariable("id") int id){
            return reqs.getObjects(id);
    }

    @CrossOrigin
    @PostMapping("requests/objects")
    public void addObject(@RequestBody spring.requests.ObjectIncas obj){
        ObjectIncas tmp = new ObjectIncas(obj.getTime(),obj.getTypeOfPutting(),obj.getPeriodOfService(),obj.getDayOfWeek(),
                obj.getCountOfMoney(),obj.getCodeOfCurrency(),obj.getTelephoneHead(),obj.getDate(),obj.getReq_id());
        tmp.setR(reqs.get(obj.getReq_id()));
        objs.add(tmp);
    }

    @CrossOrigin
    @PostMapping("requests")
    public void addReq(@RequestBody spring.requests.Request req){
        Request res = new Request(req.getBank(),req.getInn(),req.getKpp(),req.getNameOrganization(),req.getOgrn(),req.getNameEmploye(),
                req.getTelephoneEmploye(),req.getBankDetails(),req.getAccountNumber(),req.getBik(),req.getBankNumber(),req.getNameBank(),
                req.getSwift(),req.getUser());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        res.addHidden(format.format(new Date()),"Создана","Инкасация");
        User tmp = users.get(req.getUser());
        res.setU(tmp);
        res.setObjectIncases(new HashSet<ObjectIncas>());
        reqs.add(res);
    }

    @CrossOrigin
    @PutMapping("requests/id/{id}")
    public void updateReq(@PathVariable("id") int id, @RequestBody spring.requests.Request req){

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        reqs.update(req,id,format.format(new Date()),"Создана","Изменение реквизитов договора");
    }

    @CrossOrigin
    @DeleteMapping("requests/user/{user}/id/{id}")
    public void deleteReq(@PathVariable("id") int id, @PathVariable("user") int user){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        spring.requests.Request tmp = new spring.requests.Request("","","","","","","","","","","","","",user);
        reqs.update(tmp,id,format.format(new Date()),"Удалена","");
    }
}