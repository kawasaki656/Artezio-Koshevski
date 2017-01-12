package spring.dao.dictionaries;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import spring.model.dictionaries.TypeSendMoney;
import spring.utils.HibernateUtil;

import java.util.List;

/**
 * Created by Саша on 08.01.2017.
 */
public class TypeSendMoneyDAO implements spring.DAO_interface.dictionaries.TypeSendMoney{

    public List<TypeSendMoney> all(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<TypeSendMoney> result = session.createQuery("from TypeSendMoney ").list();
        Hibernate.initialize(result);
        session.getTransaction().commit();
        return result;
    }

    public TypeSendMoney getById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        TypeSendMoney result = (TypeSendMoney) session.get(TypeSendMoney.class,id);
        session.getTransaction().commit();
        return result;
    }

    public void add(TypeSendMoney tsm){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(tsm);
        session.getTransaction().commit();
    }

    public void update(TypeSendMoney tsm){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<TypeSendMoney> all = session.createQuery("from spring.model.dictionaries.TypeSendMoney").list();
        for(TypeSendMoney tmp:all){
            if(tmp.getId() == tsm.getId()){
                tmp.setValue(tsm.getValue());
                session.update(tmp);
                session.getTransaction().commit();
            }
        }
    }
}
