package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.dao.ObjectDAO;
import spring.dao.UserDAO;
import spring.model.ObjectIncas;

import java.util.ArrayList;
import java.util.List;
import spring.model.User;
import spring.requests.Registration;
import spring.requests.Login;

/**
 * Created by Саша on 31.12.2016.
 */
@Controller
public class Users {

    UserDAO users = new UserDAO();

    @CrossOrigin
    @RequestMapping(value = "/dictionaries",method = RequestMethod.GET)
    public String dictionaries(@CookieValue(value = "session",required = false) String cookie){
        if(users.typeSession(cookie).equals("admin"))
            return "dictionaries";
        else return "not found";
    }

    @CrossOrigin
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String main(@CookieValue(value = "session",required = false) String cookie){
        if(cookie == null || cookie.equals("0"))
            return "registration";
        else if(users.typeSession(cookie).equals("user"))
            return "user";
        else if(users.typeSession(cookie).equals("admin"))
            return "admin";
        return "error";
    }

    @CrossOrigin
    @RequestMapping(value = "/login/id",method = RequestMethod.GET)
    public ResponseEntity getIdByName(@CookieValue(value = "name",required = false) String cookie){
        return new ResponseEntity(users.getIdByName(cookie),HttpStatus.OK);
    }



    @CrossOrigin
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Login login ){
        if(users.updateSession(login.getLogin(),login.getPass(),login.getSession())){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else
            return new ResponseEntity(false,HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public void registration(@RequestBody Registration req){
        User tmp = new User(req.getLogin(),req.getPass(),"0","user");
        users.add(tmp);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public ResponseEntity logout(@CookieValue(value = "session",required = false) String cookie){
        return new ResponseEntity(users.exitSession(cookie),HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/testlogout",method = RequestMethod.POST)
    public void logout(){
        users.exitSession("1483569700257");
    }
}
