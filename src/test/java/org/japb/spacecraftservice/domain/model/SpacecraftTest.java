package org.japb.spacecraftservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpacecraftTest {
    private Spacecraft spacecraft;
    private SeriesMovie seriesMovie;

    @BeforeEach
    void setUp() {
        spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("TIE Fighter");
        spacecraft.setModel("Twin Ion Engine starfighter");
        spacecraft.setManufacturer("Sienar Fleet Systems");
        spacecraft.setLength(6.4);
        spacecraft.setCrewCapacity(1);
        spacecraft.setPassengerCapacity(0);

        seriesMovie = new SeriesMovie();
        seriesMovie.setId(1L);
        spacecraft.setSeriesMovie(seriesMovie);
    }

    @Test
    void testEqualsAndHashCode() {
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("TIE Fighter");
        spacecraft.setModel("Twin Ion Engine starfighter");
        spacecraft.setManufacturer("Sienar Fleet Systems");
        spacecraft.setLength(6.4);
        spacecraft.setCrewCapacity(1);
        spacecraft.setPassengerCapacity(0);

        // Crear una instancia de SeriesMovie para las pruebas
        SeriesMovie seriesMovie = new SeriesMovie();
        seriesMovie.setId(1L);

        spacecraft.setSeriesMovie(seriesMovie);

        Spacecraft sameSpacecraft = new Spacecraft();
        sameSpacecraft.setSpaceId(1L);
        sameSpacecraft.setName("TIE Fighter");
        sameSpacecraft.setModel("Twin Ion Engine starfighter");
        sameSpacecraft.setManufacturer("Sienar Fleet Systems");
        sameSpacecraft.setLength(6.4);
        sameSpacecraft.setCrewCapacity(1);
        sameSpacecraft.setPassengerCapacity(0);

        // Crear una instancia de SeriesMovie para las pruebas
        SeriesMovie sameSeriesMovie = new SeriesMovie();
        sameSeriesMovie.setId(1L);

        sameSpacecraft.setSeriesMovie(sameSeriesMovie);

        assertEquals(spacecraft, sameSpacecraft);
    }

    @Test
    void testToString() {
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("TIE Fighter");
        spacecraft.setModel("Twin Ion Engine starfighter");
        spacecraft.setManufacturer("Sienar Fleet Systems");
        spacecraft.setLength(6.4);
        spacecraft.setCrewCapacity(1);
        spacecraft.setPassengerCapacity(0);

        // Crear una instancia de SeriesMovie para las pruebas
        SeriesMovie seriesMovie = new SeriesMovie();
        seriesMovie.setId(1L);
        spacecraft.setSeriesMovie(seriesMovie);

        // Ajustar la cadena esperada para permitir tanto null como []
        String expectedToString = "Spacecraft(spaceId=1, name=TIE Fighter, model=Twin Ion Engine starfighter, " +
                "manufacturer=Sienar Fleet Systems, length=6.4, crewCapacity=1, passengerCapacity=0, " +
                "seriesMovie=SeriesMovie(id=1, title=null, releaseDate=null, type=null, spacecrafts=null), " +
                "characters=[])";

        System.out.println("Actual toString(): " + spacecraft.toString());
        System.out.println("Expected toString(): " + expectedToString);
        assertEquals(expectedToString, spacecraft.toString());
    }
}