package com.firstprojct.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InteractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void likePost_toggle() throws Exception {
        // First like — should return liked=true and likeCount increases
        mockMvc.perform(post("/api/posts/3/like").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.liked").value(true))
                .andExpect(jsonPath("$.likeCount").isNumber());

        // Second like (toggle off) — should return liked=false
        mockMvc.perform(post("/api/posts/3/like").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.liked").value(false));
    }

    @Test
    void favoritePost_toggle() throws Exception {
        // First favorite
        mockMvc.perform(post("/api/posts/3/favorite").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.favorited").value(true))
                .andExpect(jsonPath("$.favoriteCount").isNumber());

        // Toggle off
        mockMvc.perform(post("/api/posts/3/favorite").param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.favorited").value(false));
    }

    @Test
    void likeComment_toggle() throws Exception {
        // Like comment 2 (no existing like from user 3)
        mockMvc.perform(post("/api/comments/2/like").param("userId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.liked").value(true))
                .andExpect(jsonPath("$.likeCount").isNumber());

        // Toggle off
        mockMvc.perform(post("/api/comments/2/like").param("userId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.liked").value(false));
    }

    @Test
    void unlikePost_existingSeedData() throws Exception {
        // User 3 already liked post 1 in seed data — toggle should unlike
        mockMvc.perform(post("/api/posts/1/like").param("userId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.liked").value(false));
    }
}
