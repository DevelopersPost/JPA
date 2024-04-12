import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HelloWorldTest {

    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeAndLoadMessage(){
        SessionFactory sessionFactory = createSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Message message = new Message();
        message.setText("Hello World");
        session.persist(message);
        session.getTransaction().commit();

        session.beginTransaction();
        CriteriaQuery<Message> query = session.getCriteriaBuilder().createQuery(Message.class);
        query.from(Message.class);

        List<Message> resultList = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        resultList.stream().forEach(x -> System.out.println(x.getText()));

    }
}
