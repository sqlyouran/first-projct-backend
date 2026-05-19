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
class MockUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMockUsers_returnsAllUsers() throws Exception {
        mockMvc.perform(get("/api/mock-users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].nickname").isString())
                .andExpect(jsonPath("$[0].avatarUrl").isString());
    }

    @Test
    void getMockUsers_containsExpectedUser() throws Exception {
        mockMvc.perform(get("/api/mock-users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nickname", hasItem("张医生")));
    }
}
