package com.firstprojct.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void register_success() throws Exception {
        String json = """
                {
                    "email": "newuser_auth_test@example.com",
                    "password": "password123",
                    "nickname": "新用户"
                }
                """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isString())
                .andExpect(jsonPath("$.refreshToken").isString())
                .andExpect(jsonPath("$.user.email").value("newuser_auth_test@example.com"))
                .andExpect(jsonPath("$.user.nickname").value("新用户"));
    }

    @Test
    void register_duplicateEmail_returns409() throws Exception {
        // user1@example.com is seeded from V5 migration
        String json = """
                {
                    "email": "user1@example.com",
                    "password": "password123",
                    "nickname": "重复用户"
                }
                """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void login_success() throws Exception {
        // First register a user, then login
        String registerJson = """
                {
                    "email": "login_test_user@example.com",
                    "password": "securePass123",
                    "nickname": "LoginTest"
                }
                """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated());

        // Now login with same credentials
        String loginJson = """
                {
                    "email": "login_test_user@example.com",
                    "password": "securePass123"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isString())
                .andExpect(jsonPath("$.refreshToken").isString())
                .andExpect(jsonPath("$.user.email").value("login_test_user@example.com"));
    }

    @Test
    void login_wrongPassword_returns401() throws Exception {
        String json = """
                {
                    "email": "user1@example.com",
                    "password": "wrongpassword"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void login_nonexistentEmail_returns401() throws Exception {
        String json = """
                {
                    "email": "nonexistent@example.com",
                    "password": "password123"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user1@example.com", roles = {"USER"})
    void me_authenticated_returnsProfile() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user1@example.com"))
                .andExpect(jsonPath("$.nickname").isString())
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void me_unauthenticated_returns401() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
                .andExpect(status().isUnauthorized());
    }
}
