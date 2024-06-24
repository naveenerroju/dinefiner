package com.naveen.dinefiner.controller;

import com.naveen.dinefiner.constants.Endpoints;
import com.naveen.dinefiner.dto.GetRestaurantsRequest;
import com.naveen.dinefiner.dto.GetRestaurantsResponse;
import com.naveen.dinefiner.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@Slf4j
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(Endpoints.RESTAURANT_API_ENDPOINT + Endpoints.RESTAURANTS_API)
    public ResponseEntity<GetRestaurantsResponse> getRestaurants(
            @RequestParam @Valid GetRestaurantsRequest getRestaurantsRequest) {

        log.info("getRestaurants called with {}", getRestaurantsRequest);
        GetRestaurantsResponse getRestaurantsResponse;

        getRestaurantsResponse =
                restaurantService.findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
        log.info("getRestaurants returned {}", getRestaurantsResponse);

        return ResponseEntity.ok().body(getRestaurantsResponse);
    }

}
