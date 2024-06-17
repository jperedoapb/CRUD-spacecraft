package org.japb.spacecraftservice.application.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.application.service.SpacecraftService;
import org.japb.spacecraftservice.infrastructure.adapter.rest.SpacecraftController;
import org.japb.spacecraftservice.infrastructure.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(SpacecraftController.class)
@SpringBootTest
@AutoConfigureMockMvc
//@Import(SecurityConfig.class)
//@TestPropertySource(locations = "classpath:application-test.yaml")
class SpacecraftControllerSpecTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpacecraftService spacecraftService;

    @Autowired
    private ObjectMapper objectMapper;

    private SpacecraftDTO spacecraftDTO;

    @Autowired
    private InMemoryUserDetailsManager user;

    @BeforeEach
    void setUp() {
        spacecraftDTO = new SpacecraftDTO();
        spacecraftDTO.setSpaceId(1L);
        spacecraftDTO.setName("TIE Fighter");
        spacecraftDTO.setModel("Twin Ion Engine starfighter");
        spacecraftDTO.setManufacturer("Sienar Fleet Systems");
        spacecraftDTO.setLength(6.4);
        spacecraftDTO.setCrewCapacity(1);
        spacecraftDTO.setPassengerCapacity(0);
        spacecraftDTO.setSeriesMovieId(1L);
    }



    // Método para obtener las credenciales codificadas en base64
    private String getBase64Credentials(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Test
    @WithMockUser(username = "Jaime", password = "2030201811*",roles = {"USER"})
    void testCreateSpacecraft() throws Exception {
        given(spacecraftService.createSpacecraft(any(SpacecraftDTO.class))).willReturn(spacecraftDTO);

        mockMvc.perform(post("/api/spacecrafts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spacecraftDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("TIE Fighter"));
    }

    @Test
    void testGetAllSpacecrafts() throws Exception {
        List<SpacecraftDTO> spacecraftDTOList = Arrays.asList(spacecraftDTO);
        given(spacecraftService.getAllSpacecrafts()).willReturn(spacecraftDTOList);

        mockMvc.perform(get("/api/spacecrafts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("TIE Fighter"));
    }

    @Test
    void testGetSpacecraftById() throws Exception {
        given(spacecraftService.getSpacecraftById(1L)).willReturn(spacecraftDTO);

        mockMvc.perform(get("/api/spacecrafts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TIE Fighter"));
    }

    @Test
    void testUpdateSpacecraft() throws Exception {
        given(spacecraftService.updateSpacecraft(eq(1L), any(SpacecraftDTO.class))).willReturn(spacecraftDTO);

        mockMvc.perform(put("/api/spacecrafts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spacecraftDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TIE Fighter"));
    }

    @Test
    void testDeleteSpacecraft() throws Exception {
        mockMvc.perform(delete("/api/spacecrafts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testPartiallyUpdateSpacecraft() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated TIE Fighter");
        updates.put("model", "Updated Twin Ion Engine starfighter");

        SpacecraftDTO updatedSpacecraftDTO = new SpacecraftDTO();
        updatedSpacecraftDTO.setSpaceId(1L);
        updatedSpacecraftDTO.setName("Updated TIE Fighter");
        updatedSpacecraftDTO.setModel("Updated Twin Ion Engine starfighter");
        updatedSpacecraftDTO.setManufacturer("Sienar Fleet Systems");
        updatedSpacecraftDTO.setLength(6.4);
        updatedSpacecraftDTO.setCrewCapacity(1);
        updatedSpacecraftDTO.setPassengerCapacity(0);
        updatedSpacecraftDTO.setSeriesMovieId(1L);

        given(spacecraftService.partiallyUpdateSpacecraft(eq(1L), any(Map.class))).willReturn(updatedSpacecraftDTO);

        mockMvc.perform(patch("/api/spacecrafts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated TIE Fighter"))
                .andExpect(jsonPath("$.model").value("Updated Twin Ion Engine starfighter"));
    }

     */
}