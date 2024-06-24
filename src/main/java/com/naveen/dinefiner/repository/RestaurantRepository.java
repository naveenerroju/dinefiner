package com.naveen.dinefiner.repository;

import java.time.LocalTime;
import java.util.List;

import com.naveen.dinefiner.entity.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {

    List<RestaurantEntity> findAllRestaurantsCloseBy(Double latitude,
                                                     Double longitude, LocalTime currentTime, Double servingRadiusInKms);

}

