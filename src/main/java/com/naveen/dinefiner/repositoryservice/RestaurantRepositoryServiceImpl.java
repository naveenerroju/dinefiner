package com.naveen.dinefiner.repositoryservice;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.naveen.dinefiner.dto.Coordinates;
import com.naveen.dinefiner.dto.Restaurant;
import com.naveen.dinefiner.entity.RestaurantEntity;
import com.naveen.dinefiner.repository.RestaurantRepository;
import com.naveen.dinefiner.utils.GeoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    RestaurantRepository respo;

    @Autowired
    private ModelMapper modelMapper;

    private boolean isOpenNow(LocalTime time, RestaurantEntity res) {
        LocalTime openingTime = LocalTime.parse(res.getOpensAt());
        LocalTime closingTime = LocalTime.parse(res.getClosesAt());

        return time.isAfter(openingTime) && time.isBefore(closingTime);
    }

    @Override
    public List<Restaurant> findAllRestaurantsCloseBy(Coordinates coordinates,
                                                      LocalTime currentTime,
                                                      Double servingRadiusInKms) {
        List<RestaurantEntity> allRestaurants = respo.findAll();
        List<Restaurant> nearbyOpenRestaurants = new ArrayList<>();

        for (RestaurantEntity restaurantEntity : allRestaurants) {
            if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, coordinates.getLatitude(), coordinates.getLongitude(), servingRadiusInKms)) {
                Restaurant restaurant = modelMapper.map(restaurantEntity, Restaurant.class);
                nearbyOpenRestaurants.add(restaurant);
            }
        }

        return nearbyOpenRestaurants;
    }

    /**
     * Utility method to check if a restaurant is within the serving radius at a given time.
     *
     * @return boolean True if restaurant falls within serving radius and is open, false otherwise
     */
    private boolean isRestaurantCloseByAndOpen(RestaurantEntity restaurantEntity,
                                               LocalTime currentTime, Double latitude, Double longitude, Double servingRadiusInKms) {
        if (isOpenNow(currentTime, restaurantEntity)) {
            return GeoUtils.findDistanceInKm(latitude, longitude,
                    restaurantEntity.getCoordinates().getLatitude(), restaurantEntity.getCoordinates().getLongitude())
                    < servingRadiusInKms;
        }

        return false;
    }


}

