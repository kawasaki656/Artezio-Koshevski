package spring.dao;

import spring.model.ObjectIncas;
import spring.model.Request;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import spring.utils.HybernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 02.01.2017.
 */
public class RequestDAO implements spring.DAO_interface.Request {

    public List<Request> all(){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Request> result = session.createQuery("from Request").list();

        // Насильная инициализация списка. Не очень хорошая практика так делать
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public Request get(int id){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Request result = session.get(Request.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(Request r){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
    }

    public void update(spring.requests.Request r, int id, String date, String status, String type ) {
        Session session = HybernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<spring.model.Request> all = session.createQuery("from spring.model.Request").list();
        Hibernate.initialize(all);
        session.getTransaction().commit();

        for (spring.model.Request req : all) {
            if (req.getId().equals(id)) {
                req.setBank(r.getBank());
                req.setInn(r.getInn());
                req.setKpp(r.getKpp());
                req.setNameOrganization(r.getNameOrganization());
                req.setOgrn(r.getOgrn());
                req.setNameEmploye(r.getNameEmploye());
                req.setTelephoneEmploye(r.getTelephoneEmploye());
                req.setBankDetails(r.getBankDetails());
                req.setAccountNumber(r.getAccountNumber());
                req.setBik(r.getBik());
                req.setBankNumber(r.getBankNumber());
                req.setNameBank(r.getNameBank());
                req.setSwift(r.getSwift());
                req.setDate(date);
                req.setStatus(status);
                req.setType(type);

                session.beginTransaction();
                session.update(req);
                session.getTransaction().commit();
            }
        }
    }

    public void delete(int id){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(Request.class,id));

        session.getTransaction().commit();
    }

    public Set<ObjectIncas> getObjects(int id){
        Session session = HybernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Request tmp = session.get(Request.class,id);
        Set<ObjectIncas> result = tmp.getObjectIncases();
        Set<ObjectIncas> newResult = new HashSet<ObjectIncas>();
        for(ObjectIncas o : result)
        {
            o.setR(null);
            newResult.add(o);
        }
        //Hibernate.initialize(result);
        session.getTransaction().commit();
        return newResult;
    }
}
