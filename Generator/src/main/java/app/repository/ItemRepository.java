package app.repository;

import app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {

   List<Item> findByMetricWeight(double weight);

}
