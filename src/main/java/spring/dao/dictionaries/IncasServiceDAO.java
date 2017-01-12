package spring.dao.dictionaries;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import spring.model.dictionaries.IncasService;
import spring.utils.HibernateUtil;

import java.util.List;

/**
 * Created by Саша on 07.01.2017.
 */
public class IncasServiceDAO implements spring.DAO_interface.dictionaries.IncasService{

    public List<IncasService> all(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<IncasService> result = session.createQuery("from IncasService ").list();
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public IncasService getById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        IncasService result = (IncasService) session.get(IncasService.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(IncasService is){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(is);
        session.getTransaction().commit();
    }

    public void update(IncasService is){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<IncasService> all = session.createQuery("from spring.model.dictionaries.IncasService").list();
        for(IncasService tmp:all){
            if(tmp.getId() == is.getId()){
                tmp.setValue(is.getValue());
                session.update(tmp);
                session.getTransaction().commit();
            }
        }
    }
}
