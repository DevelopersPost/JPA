import com.example.Message;
import configuration.SpringDataConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class NativeHibernateToJPASwitchTest {

    @Autowired
    SessionFactory sessionFactory;

/*
    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
*/

    @Test
    public void HibernateToJpaSwitchTest(){
        //SessionFactory sessionFactory = createSessionFactory();
        EntityManagerFactory emf = sessionFactory.unwrap(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Message message = new Message();
        message.setText("Hello World");

        em.persist(message);
        em.getTransaction().commit();

        List<Message> testMessage = em.createQuery("select m from Message m", Message.class).getResultList();
        testMessage.stream().forEach(x -> System.out.println(x.getText()) );

    }

}
