package org.japb.spacecraftservice.application.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.List;

@Data
public class SpacecraftDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;

    @Positive(message = "Length must be a positive number")
    private Double length;

    @PositiveOrZero(message = "Crew capacity must be zero or a positive number")
    private Integer crewCapacity;

    @PositiveOrZero(message = "Passenger capacity must be zero or a positive number")
    private Integer passengerCapacity;

    @NotNull(message = "Series movie ID is mandatory")
    private Long seriesMovieId;

    private List<CharacterDTO> characters;
}
