package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    Long id;

    String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
