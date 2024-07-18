import app.model.*;
import app.repository.ItemRepository;
import app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingValuesJPATest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void test(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ch05.generator");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        City city = new City();
        city.setName("Boston");
        city.setZipcode(new SwissZipcode("1234"));
        city.setCountry("USA");

        User user = new User();
        user.setUsername("username");
        user.setHomeAddress(new Address("gandhi nagar",city));

        Item item = new Item();
        item.setName("some item");
        item.setMetricWeight(2);
        item.setBuyNowPrice(new MonetaryAmount(new BigDecimal(1.1), Currency.getInstance("USD")));
        item.setDescription("descriptiondescription");
        item.setInitialPrice(new MonetaryAmount(new BigDecimal("1.00"),Currency.getInstance("USD")));
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.valueOf(1.1), Currency.getInstance("USD")));


        em.persist(user);
        em.persist(item);

        em.getTransaction().commit();
        em.refresh(user);
        em.refresh(item);

        em.getTransaction().begin();

        List<User> users = em.createQuery("select u from User u", User.class).getResultList();

        TypedQuery<Item> query = em.createQuery("select i from Item i where i.metricWeight = :w", Item.class);
        query.setParameter("w",2.0);
        List<Item> items = query.getResultList();
        System.out.println(items.get(0).getInitialPrice());
        Assertions.assertAll(() -> Assertions.assertEquals(1,users.size()),
                () -> Assertions.assertEquals(1,items.size()));
    }


    @Test
    public void test1(){
        City city = new City();
        city.setName("Boston");
        city.setZipcode(new GermanZipcode("12345"));
        city.setCountry("USA");

        User user = new User();
        user.setUsername("username");
        user.setHomeAddress(new Address("gandhi nagar",city));

        Item item = new Item();
        item.setName("some item");
        item.setMetricWeight(2);
        item.setBuyNowPrice(new MonetaryAmount(new BigDecimal(1.1), Currency.getInstance("USD")));
        item.setDescription("descriptiondescription");

        userRepository.save(user);
        itemRepository.save(item);

        List<User> users = (List<User>) userRepository.findAll();
        List<Item> items = itemRepository.findByMetricWeight(2);

        System.out.println(users.get(0).getHomeAddress().getCity().getZipcode().getValue());
        Assertions.assertAll(() -> Assertions.assertEquals(1,users.size()),
                () -> Assertions.assertEquals(1,items.size()));
    }
}
