package com.naveen.dinefiner.entity;

import com.naveen.dinefiner.dto.Coordinates;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "restaurants")
@NoArgsConstructor
public class RestaurantEntity {

    @Id
    private String id;

    @NotNull
    private String restaurantId;

    @NotNull
    private String name;

    @NotNull
    private String city;

    private String imageUrl;

    @NotNull
    private Coordinates coordinates;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;

    private List<String> attributes = new ArrayList<>();

}

