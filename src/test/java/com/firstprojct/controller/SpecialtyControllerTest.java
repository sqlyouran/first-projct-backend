package com.firstprojct.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listSpecialties_returnsAllSpecialties() throws Exception {
        mockMvc.perform(get("/api/specialties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(jsonPath("$[0].name").value("Orthopedics"))
                .andExpect(jsonPath("$[0].nameCn").value("骨科"));
    }

    @Test
    void getSpecialtyRankings_returnsRankedHospitals() throws Exception {
        mockMvc.perform(get("/api/specialties/1/rankings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialty.name").value("Orthopedics"))
                .andExpect(jsonPath("$.year").value(2023))
                .andExpect(jsonPath("$.rankings", hasSize(10)))
                .andExpect(jsonPath("$.rankings[0].rankPosition").value(1))
                .andExpect(jsonPath("$.rankings[0].hospital.name").value("Peking University Third Hospital"));
    }

    @Test
    void getSpecialtyRankings_filterByCity() throws Exception {
        mockMvc.perform(get("/api/specialties/1/rankings").param("city", "Beijing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rankings").isArray())
                .andExpect(jsonPath("$.rankings[*].hospital.city", everyItem(equalToIgnoringCase("Beijing"))));
    }

    @Test
    void getSpecialtyRankings_notFound() throws Exception {
        mockMvc.perform(get("/api/specialties/999/rankings"))
                .andExpect(status().isNotFound());
    }
}
