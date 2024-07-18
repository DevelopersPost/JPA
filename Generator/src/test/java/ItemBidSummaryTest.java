import app.model.Bid;
import app.model.Item;
import app.model.ItemBidSummary;
import app.repository.BidRepository;
import app.repository.ItemBidSummaryRepository;
import app.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class ItemBidSummaryTest {

    @Test
    public void test(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ch05.generator");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Item item = new Item();
        item.setName("some item");
        item.setAuctionEnd(new Date());

        Bid bid = new Bid(new BigDecimal(1000.0),item);
        Bid bid1 = new Bid(new BigDecimal(2000),item);

        entityManager.persist(item);
        entityManager.persist(bid);
        entityManager.persist(bid1);

        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();

        TypedQuery<ItemBidSummary> query = entityManager.createQuery("select ibs from ItemBidSummary ibs where ibs.id = :id",
                ItemBidSummary.class);
        ItemBidSummary itemBidSummary = query.setParameter("id", 1000L).getSingleResult();
        entityManager.getTransaction().commit();
        assertAll(() -> assertEquals(1000L,itemBidSummary.getItemId()));
    }

    @Autowired
    ItemBidSummaryRepository itemBidSummaryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BidRepository bidRepository;

    @Test
    public void test1(){
        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());

        Bid bid1 = new Bid(new BigDecimal(1000.0), item);
        Bid bid2 = new Bid(new BigDecimal(1100.0), item);

        itemRepository.save(item);
         bidRepository.save(bid1);
        bidRepository.save(bid2);

        Optional<ItemBidSummary> itemBidSummary = itemBidSummaryRepository.findById(1000L);

        assertAll(
                () -> assertEquals(1000, itemBidSummary.get().getItemId()),
                () -> assertEquals("Some Item", itemBidSummary.get().getName()),
                () -> assertEquals(2, itemBidSummary.get().getNumberOfBids())
        );
    }
}
