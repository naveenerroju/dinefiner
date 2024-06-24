package com.naveen.dinefiner.service;


import com.naveen.dinefiner.dto.Coordinates;
import com.naveen.dinefiner.dto.GetRestaurantsRequest;
import com.naveen.dinefiner.dto.GetRestaurantsResponse;
import com.naveen.dinefiner.dto.Restaurant;
import com.naveen.dinefiner.repositoryservice.RestaurantRepositoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

    @Value("${normalHoursServingRadiusInKm}")
    private Double normalHoursServingRadiusInKms;
    @Value("${peakHoursServingRadiusInKms}")
    private Double peakHoursServingRadiusInKms;

    @Autowired
    private RestaurantRepositoryService restaurantRepositoryService;

    @Override
    public GetRestaurantsResponse findAllRestaurantsCloseBy(
            GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {

        Double hourOfServing = (currentTime.isAfter(LocalTime.of(07, 59)) && currentTime.isBefore(LocalTime.of(10, 01)))
                || (currentTime.isAfter(LocalTime.of(12, 59)) && currentTime.isBefore(LocalTime.of(14, 01)))
                || (currentTime.isAfter(LocalTime.of(18, 59)) && currentTime.isBefore(LocalTime.of(21, 01)))
                ? peakHoursServingRadiusInKms : normalHoursServingRadiusInKms;

        List<Restaurant> restaurants = restaurantRepositoryService
                .findAllRestaurantsCloseBy(
                        new Coordinates(getRestaurantsRequest.getLatitude(),
                                getRestaurantsRequest.getLongitude()
                        ), currentTime, hourOfServing);

        return new GetRestaurantsResponse(restaurants);
    }


}
