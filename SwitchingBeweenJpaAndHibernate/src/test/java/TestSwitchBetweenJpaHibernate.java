import configuration.SpringDataConfiguration;
import entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
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
public class TestSwitchBetweenJpaHibernate {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Test
    public void testSwitchFromJpaToHibernate(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Message message = new Message();
        message.setId(1L);
        message.setText("Hello1");
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        entityManager.close();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        System.out.println(session.createNativeQuery("select text from message").getResultList().get(0));
    }

}
