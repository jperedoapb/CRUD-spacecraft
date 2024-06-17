package org.japb.spacecraftservice.infrastructure.config;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestSecurityConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void indexWhenUnAuthenticatedThenRedirect() throws Exception {
        // @formatter:off
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
        // @formatter:on
    }
/*
    @Test
    @WithMockUser(username = "Jaime", password = "2030201811*",roles = {"USER"})
    void indexWhenAuthenticatedThenOk() throws Exception {
        // @formatter:off
        this.mockMvc.perform(get("/api/spacecrafts/"))
                .andExpect(status().isOk());
        // @formatter:on
    }

 */
}
