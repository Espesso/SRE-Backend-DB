package com.example.item.controller;

import com.example.item.entity.Item;
import com.example.item.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Item> getAll() {
        return repo.findAll();
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        List<Item> foundItems = repo.findByNameContainingIgnoreCase(name);

        if (foundItems.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "ไม่พบสินค้าที่ชื่อ: " + name));
        }

        return ResponseEntity.ok(foundItems);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Item item) {

        if (item.getName() == null || item.getName().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "ชื่อสินค้าไม่สามารถเว้นว่างได้ ❌"));
        }

        if (repo.existsByNameIgnoreCase(item.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "มีชื่อสินค้านี้อยู่แล้วในระบบ ❌"));
        }

        Item savedItem = repo.save(item);
        return ResponseEntity.status(201).body(savedItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}