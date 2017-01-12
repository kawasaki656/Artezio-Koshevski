package spring.dao;


import spring.model.Request;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import spring.model.User;
import spring.utils.HibernateUtil;

import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 02.01.2017.
 */
public class UserDAO implements spring.DAO_interface.User {

    public void add(spring.model.User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public User get(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User result = session.get(User.class,id);
        session.getTransaction().commit();
        return result;
    }

    public int getIdByName(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "FROM User U WHERE U.login = :user_login";
        Query query = session.createQuery(hql);
        query.setParameter("user_login",name);
        List<User> user = query.list();
        session.getTransaction().commit();
        return user.get(0).getId();
    }

    public Set<Request> getRequests(String id_ses) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "FROM User U WHERE U.session = :user_session";

        Query query = session.createQuery(hql);
        query.setParameter("user_session",id_ses);

        List<User> user = query.list();
        int res_id = user.iterator().next().getId();

        User tmp = (spring.model.User) session.get(User.class,res_id);
        Set<Request> result = tmp.getRequests();
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public boolean existSession(String id_ses){
        if(id_ses.equals("0"))
            return false;
        else {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "FROM User U WHERE U.session = :user_session";

            Query query = session.createQuery(hql);
            query.setParameter("user_session", id_ses);

            List<spring.model.User> user = query.list();
            session.getTransaction().commit();

            return !user.isEmpty();
        }
    }

    public String typeSession(String id_ses){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "FROM User U WHERE U.session = :user_session";

            Query query = session.createQuery(hql);
            query.setParameter("user_session", id_ses);

            List<spring.model.User> user = query.list();
            session.getTransaction().commit();
            if(user.isEmpty())
                return "error";
            else return user.get(0).getRole();
    }

    public boolean updateSession(String login, String pass, String ses) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<spring.model.User> all = session.createQuery("from spring.model.User").list();
        Hibernate.initialize(all);
        session.getTransaction().commit();

        for(spring.model.User user : all){
            if(user.getLogin().equals(login) && user.getPass().equals(pass)) {
                user.setSession(ses);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
                return true;
            }

            /*else if(user.getLogin().equals(login) && user.getPass().equals(pass) && !user.getSession().equals("0")){
                return true;
            }*/
        }

        return false;
    }

    public boolean exitSession(String ses) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<spring.model.User> all = session.createQuery("from spring.model.User").list();
        Hibernate.initialize(all);

        for(spring.model.User user : all){
            if(user.getSession().equals(ses)) {
                user.setSession("0");
                session.update(user);
                session.getTransaction().commit();
                return true;
            }
        }

        return false;
    }
}
