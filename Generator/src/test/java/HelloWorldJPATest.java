import app.model.Item;
import app.repository.ItemRepository;
import org.hibernate.hql.spi.id.local.Helper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class HelloWorldJPATest {

    @Test
    void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch05.generator");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Item item = new Item();
            item.setName("some item");
            item.setAuctionEnd(new Date());
            em.persist(item);
            em.getTransaction().commit();

            em.getTransaction().begin();
            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
            em.getTransaction().commit();

            assertAll(() -> assertEquals(1,items.size()),() -> assertEquals("some item",items.get(0).getName()));
        }catch (Exception e){

        }
    }

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void test1(){
        Item item = new Item();
        item.setName("some item");
        item.setAuctionEnd(new Date());

        itemRepository.save(item);
        List<Item> items = (List<Item>) itemRepository.findAll();
        assertAll(() -> assertEquals(1,items.size()),
                () -> assertEquals("some item", items.get(0).getName()));
    }
}
