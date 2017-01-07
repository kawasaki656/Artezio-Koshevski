package spring.utils; /**
 * Created by Саша on 02.01.2017.
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import spring.model.ObjectIncas;
import spring.model.Request;
import spring.model.User;


public class HybernateUtil {


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration()
                    .addAnnotatedClass(ObjectIncas.class)
                    .addAnnotatedClass(Request.class)
                    .addAnnotatedClass(User.class)
                    .configure();


            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }
}