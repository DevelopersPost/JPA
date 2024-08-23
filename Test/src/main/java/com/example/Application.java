package com.example;

import com.example.model.Bid;
import com.example.model.Item;
import com.example.model.User;
import com.example.repositories.BidRepository;
import com.example.repositories.ItemRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BidRepository bidRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner dbOperation() {
        return x -> {

            User john = new User("John Smith");
            userRepository.save(john);

            Item item = new Item("Foo");

            Bid bid = new Bid(BigDecimal.valueOf(100), item);
            Bid bid2 = new Bid(BigDecimal.valueOf(200), item);

            item.addBid(bid);
            bid.setBidder(john);
            item.addBid(bid2);
            bid2.setBidder(john);

            itemRepository.save(item);

            List<Item> items = itemRepository.findAll();
            Set<Bid> bids = bidRepository.findByItem(item);
            User user = userRepository.findUserWithBids(john.getId());



        };
    }
}
