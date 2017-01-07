package spring.dao;

import org.hibernate.Hibernate;
import spring.DAO_interface.Object;
import org.hibernate.Session;
import spring.utils.HybernateUtil;
import spring.model.ObjectIncas;


import java.util.List;

/**
 * Created by Саша on 02.01.2017.
 */
public class ObjectDAO implements Object {
    public List<ObjectIncas> all(){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<ObjectIncas> result = session.createQuery("from ObjectIncas").list();

        // Насильная инициализация списка. Не очень хорошая практика так делать
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public ObjectIncas get(int id){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        ObjectIncas result = (ObjectIncas) session.get(ObjectIncas.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(ObjectIncas o){
        Session session = HybernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);

        List<spring.model.Request> all = session.createQuery("from spring.model.Request").list();
        Hibernate.initialize(all);

        for (spring.model.Request req : all) {
            if (req.getId().equals(o.getReq_id())) {
                req.setType("Добавление объекта");
                session.update(req);
            }
        }
        session.getTransaction().commit();

    }

    public void delete(int id){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(ObjectIncas.class,id));
        session.getTransaction().commit();
    }
}
