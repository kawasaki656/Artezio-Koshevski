package spring.controller;

import spring.model.User;
import spring.storage.StorageUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Саша on 26.12.2016.
 */
@Controller
public class Index {

    @CrossOrigin
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getjsp(){

        return StorageUsers.curUser;
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "login") String login,
                          @RequestParam(value = "pass") String pass)
    {
        if(StorageUsers.curUser.equals("index")) {
            List<User> users = StorageUsers.users;
            Iterator<User> iterator = users.iterator();
            String tmpLogin = "";
            String tmpPass = "";
            String res = "error";

            while (iterator.hasNext()) {
                User tmp = iterator.next();
                tmpLogin = tmp.getLogin();
                tmpPass = tmp.getPass();

                if (tmpLogin.equals(login) && tmpPass.equals(pass)) {
                    res = tmp.getRole();
                    StorageUsers.curUser = res;
                    StorageUsers.curId = tmp.getId();
                    break;
                }
            }

            return res;
        }
        else {
            StorageUsers.curId=-1;
            StorageUsers.curUser = "index";
            return "index";
        }


    }
}
