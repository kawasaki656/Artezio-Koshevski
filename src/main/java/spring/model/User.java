package spring.model;

/**
 * Created by Саша on 28.12.2016.
 */
public class User {
    private String login;
    private String pass;
    private String role;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String login, String pass, String role, int id) {
        this.login = login;
        this.pass = pass;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
