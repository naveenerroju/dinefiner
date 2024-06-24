package com.naveen.dinefiner.repositoryservice;

import com.naveen.dinefiner.dto.Coordinates;
import com.naveen.dinefiner.dto.Restaurant;

import java.time.LocalTime;
import java.util.List;

public interface RestaurantRepositoryService {
    List<Restaurant> findAllRestaurantsCloseBy(Coordinates coordinates,
                                               LocalTime currentTime, Double servingRadiusInKms);

}
