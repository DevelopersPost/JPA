package app.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect(value = "SELECT I.ID AS ITEMID,I.NAME AS NAME," +
        " COUNT(B.ID) AS NUMBEROFBIDS FROM ITEM I LEFT OUTER JOIN " +
        " BID B ON B.ITEM_ID = I.ID GROUP BY I.ID,I.NAME")
@Synchronize({"ITEM","BID"})
public class ItemBidSummary {

    @Id
    private Long itemId;

    private String name;

    private Long numberOfBids;

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Long getNumberOfBids() {
        return numberOfBids;
    }
}
