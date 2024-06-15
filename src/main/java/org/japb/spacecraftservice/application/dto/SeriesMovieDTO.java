package org.japb.spacecraftservice.application.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

//@Data
public record SeriesMovieDTO(Long id,
                            @NotBlank(message = "Title is mandatory")
                            String title,
                            @PastOrPresent(message = "Release date cannot be in the future")
                            LocalDate releaseDate,
                            @NotBlank(message = "Title is mandatory")
                            String type,
                            List<SpacecraftDTO> spacecrafts
                            ) {
    /*
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private List<SpacecraftDTO> spacecrafts;

     */
}
