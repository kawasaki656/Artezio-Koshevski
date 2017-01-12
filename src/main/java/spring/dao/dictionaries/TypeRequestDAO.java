package spring.dao.dictionaries;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import spring.model.dictionaries.TypeRequest;
import spring.utils.HibernateUtil;

import java.util.List;

/**
 * Created by Саша on 08.01.2017.
 */
public class TypeRequestDAO implements spring.DAO_interface.dictionaries.TypeRequest{
    public List<TypeRequest> all(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<TypeRequest> result = session.createQuery("from TypeRequest ").list();
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public TypeRequest getById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        TypeRequest result = (TypeRequest) session.get(TypeRequest.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(TypeRequest tr){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(tr);
        session.getTransaction().commit();
    }

    public void update(TypeRequest tr){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<TypeRequest> all = session.createQuery("from spring.model.dictionaries.TypeRequest").list();
        for(TypeRequest tmp:all){
            if(tmp.getId() == tr.getId()){
                tmp.setValue(tr.getValue());
                session.update(tmp);
                session.getTransaction().commit();
            }
        }
    }
}
