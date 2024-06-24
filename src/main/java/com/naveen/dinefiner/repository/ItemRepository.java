package com.naveen.dinefiner.repository;

import java.util.Optional;

import com.naveen.dinefiner.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ItemRepository extends MongoRepository<ItemEntity, String> {
    Optional<ItemEntity> findByItemId(String itemId);
}

