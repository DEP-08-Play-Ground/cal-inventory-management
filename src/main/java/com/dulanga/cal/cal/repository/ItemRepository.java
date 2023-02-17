package com.dulanga.cal.cal.repository;

import com.dulanga.cal.cal.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    Item save(Item item);

    @Query(value = "SELECT * FROM Item", nativeQuery = true)
    List<Item> getAllItems();

    Optional<Item> getItemByItemCode(String itemCode);

    Integer deleteItemByItemCode(String itemCode);

    Item findItemByItemCode(String itemCode);

}
