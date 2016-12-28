package spring.storage;

import spring.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 28.12.2016.
 */
public class StorageUsers {
    public static List<User> users = new ArrayList<User>();
            static{
                User user1 = new User("user1","12345","user",1);
                User user2 = new User("user2","12345","user",2);
                User admin = new User("admin","admin","user",0);
                users.add(user1);
                users.add(user2);
                users.add(admin);
            }
    public static String curUser="index";
    public static int curId = -1;
}
