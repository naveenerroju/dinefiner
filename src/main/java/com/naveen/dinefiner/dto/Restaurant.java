package com.naveen.dinefiner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @NotNull
    private String restaurantId;
    @NotNull
    private String name;
    private String city;
    private String imageUrl;
    @NotNull
    private Coordinates coordinates;
    private String opensAt;
    private String closesAt;
    private List<String> attributes = new ArrayList<>();

}
