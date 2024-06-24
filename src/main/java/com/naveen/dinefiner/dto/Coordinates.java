package com.naveen.dinefiner.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {
    @NotNull
    @Min(-90)
    @Max(90)
    private Double latitude;
    @NotNull
    @Min(-180)
    @Max(180)
    private Double longitude;
}
