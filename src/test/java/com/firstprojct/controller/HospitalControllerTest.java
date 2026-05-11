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
class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchHospitals_returnsPagedResults() throws Exception {
        mockMvc.perform(get("/api/hospitals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(greaterThan(0)))
                .andExpect(jsonPath("$.content", hasSize(lessThanOrEqualTo(10))));
    }

    @Test
    void searchHospitals_filterByCity() throws Exception {
        mockMvc.perform(get("/api/hospitals").param("city", "Beijing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].city", everyItem(equalToIgnoringCase("Beijing"))));
    }

    @Test
    void searchHospitals_searchByQuery() throws Exception {
        mockMvc.perform(get("/api/hospitals").param("q", "Peking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.content[0].name", containsStringIgnoringCase("Peking")));
    }

    @Test
    void getHospital_returnsDetail() throws Exception {
        mockMvc.perform(get("/api/hospitals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Peking Union Medical College Hospital"))
                .andExpect(jsonPath("$.city").value("Beijing"))
                .andExpect(jsonPath("$.topSpecialties").isArray())
                .andExpect(jsonPath("$.topSpecialties", hasSize(greaterThan(0))));
    }

    @Test
    void getHospital_notFound() throws Exception {
        mockMvc.perform(get("/api/hospitals/999"))
                .andExpect(status().isNotFound());
    }
}
