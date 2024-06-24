package com.naveen.dinefiner.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class GetRestaurantsRequest {

    @NotNull
    @Min(-90)
    @Max(90)
    private double latitude;

    @NotNull
    @Min(-180)
    @Max(180)
    private double longitude;

}

