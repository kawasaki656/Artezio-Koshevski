package spring.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Саша on 02.01.2017.
 */

@Entity
@Table(name = "users")
public class User implements Serializable{

    @OneToMany(mappedBy = "u")
    private Set<Request> requests = new HashSet<Request>();

    public User(){

    }

    public User(String login, String pass, String session){
        this.login = login;
        this.pass = pass;
        this.session = session;
    }

    @Id
    @GenericGenerator(name="inc" , strategy="increment")
    @GeneratedValue(generator="inc")
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="pass")
    private String pass;

    @Column(name="session")
    private String session;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }
}
