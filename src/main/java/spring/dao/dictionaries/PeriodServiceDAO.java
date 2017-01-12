package spring.dao.dictionaries;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import spring.model.dictionaries.PeriodService;
import spring.utils.HibernateUtil;

import java.util.List;

/**
 * Created by Саша on 08.01.2017.
 */
public class PeriodServiceDAO implements spring.DAO_interface.dictionaries.PeriodService{

    public List<PeriodService> all(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PeriodService> result = session.createQuery("from PeriodService ").list();
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public PeriodService getById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        PeriodService result = (PeriodService) session.get(PeriodService.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(PeriodService ps){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ps);
        session.getTransaction().commit();
    }

    public void update(PeriodService ps){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<PeriodService> all = session.createQuery("from spring.model.dictionaries.PeriodService").list();
        for(PeriodService tmp:all){
            if(tmp.getId() == ps.getId()){
                tmp.setValue(ps.getValue());
                session.update(tmp);
                session.getTransaction().commit();
            }
        }
    }
}
