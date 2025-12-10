package com.example.item.repository;

import com.example.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Item> findByNameContainingIgnoreCase(String name);

}
