package org.japb.spacecraftservice.application.dto;


import jakarta.validation.constraints.*;
import java.time.LocalDate;


public record CharacterDTO (Long id,
                            @NotBlank(message = "Name is mandatory")
                            String name,
                            @NotBlank(message = "Role is mandatory")
                            String role,
                            @NotBlank(message = "Species is mandatory")
                            String species,
                            @NotBlank(message = "Gender is mandatory")
                            String gender,
                            @PastOrPresent(message = "Birth date cannot be in the future")
                            LocalDate birthDate,
                            @NotNull(message = "Spacecraft ID is mandatory")
                            Long spacecraftId
){
    /*
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @NotBlank(message = "Species is mandatory")
    private String species;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;

    @NotNull(message = "Spacecraft ID is mandatory")
    private Long spacecraftId;

     */
}
