package org.japb.spacecraftservice.application.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpacecraftDTOTest {

    private SpacecraftDTO spacecraftDTO;

    @Test
    void testEqualsAndHashCode() {
        SpacecraftDTO spacecraftDTO1 = new SpacecraftDTO();
        spacecraftDTO1.setSpaceId(1L);
        spacecraftDTO1.setName("TIE Fighter");
        spacecraftDTO1.setModel("Twin Ion Engine starfighter");
        spacecraftDTO1.setManufacturer("Sienar Fleet Systems");
        spacecraftDTO1.setLength(6.4);
        spacecraftDTO1.setCrewCapacity(1);
        spacecraftDTO1.setPassengerCapacity(0);
        spacecraftDTO1.setSeriesMovieId(1L);

        SpacecraftDTO spacecraftDTO2 = new SpacecraftDTO();
        spacecraftDTO2.setSpaceId(1L);
        spacecraftDTO2.setName("TIE Fighter");
        spacecraftDTO2.setModel("Twin Ion Engine starfighter");
        spacecraftDTO2.setManufacturer("Sienar Fleet Systems");
        spacecraftDTO2.setLength(6.4);
        spacecraftDTO2.setCrewCapacity(1);
        spacecraftDTO2.setPassengerCapacity(0);
        spacecraftDTO2.setSeriesMovieId(1L);

        // Probamos equals y hashCode
        assertEquals(spacecraftDTO1, spacecraftDTO2);
        assertEquals(spacecraftDTO1.hashCode(), spacecraftDTO2.hashCode());

        // Modificamos un campo y comprobamos que los objetos ya no son iguales
        spacecraftDTO2.setPassengerCapacity(10);
        assertNotEquals(spacecraftDTO1, spacecraftDTO2);
        assertNotEquals(spacecraftDTO1.hashCode(), spacecraftDTO2.hashCode());
    }

    @Test
    void testToString() {
        SpacecraftDTO spacecraftDTO = new SpacecraftDTO();
        spacecraftDTO.setSpaceId(1L);
        spacecraftDTO.setName("TIE Fighter");
        spacecraftDTO.setModel("Twin Ion Engine starfighter");
        spacecraftDTO.setManufacturer("Sienar Fleet Systems");
        spacecraftDTO.setLength(6.4);
        spacecraftDTO.setCrewCapacity(1);
        spacecraftDTO.setPassengerCapacity(0);
        spacecraftDTO.setSeriesMovieId(1L);

        // Probamos toString para verificar la representación del objeto
        String expectedToString = "SpacecraftDTO(spaceId=1, name=TIE Fighter, model=Twin Ion Engine starfighter, " +
                "manufacturer=Sienar Fleet Systems, length=6.4, crewCapacity=1, passengerCapacity=0, seriesMovieId=1, characters=null)";
        assertEquals(expectedToString, spacecraftDTO.toString());
    }
}