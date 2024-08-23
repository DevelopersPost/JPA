package com.example.repositories;

import com.example.model.Bid;
import com.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid,Long> {

    Set<Bid> findByItem(Item item);

}
