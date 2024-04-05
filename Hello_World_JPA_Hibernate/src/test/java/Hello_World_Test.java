import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Hello_World_Test {

    @Test
    public void storeMessage(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World");

            em.persist(message);
            em.getTransaction().commit();

            List<Message> testMessage = em.createQuery("select m from Message m", Message.class).getResultList();
            testMessage.stream().forEach(x -> System.out.println(x.getText()) );

        }finally {

        }
    }
}
