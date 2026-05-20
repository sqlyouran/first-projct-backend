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
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1@example.com", roles = {"USER"})
    void createPost_success() throws Exception {
        String json = """
                {
                    "title": "测试帖子标题",
                    "content": "测试帖子内容，分享一些看病经验。",
                    "hospitalIds": [1],
                    "specialtyIds": [1]
                }
                """;
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("测试帖子标题"))
                .andExpect(jsonPath("$.content").value("测试帖子内容，分享一些看病经验。"))
                .andExpect(jsonPath("$.authorNickname").isString())
                .andExpect(jsonPath("$.hospitals", hasSize(1)))
                .andExpect(jsonPath("$.specialties", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "user1@example.com", roles = {"USER"})
    void createPost_emptyTitle_returns400() throws Exception {
        String json = """
                {
                    "title": "",
                    "content": "有内容"
                }
                """;
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listPosts_sortByLatest() throws Exception {
        mockMvc.perform(get("/api/posts").param("sort", "latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(5))))
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].title").isString())
                .andExpect(jsonPath("$.content[0].authorNickname").isString())
                .andExpect(jsonPath("$.content[0].likeCount").isNumber())
                .andExpect(jsonPath("$.content[0].commentCount").isNumber());
    }

    @Test
    void listPosts_sortByHot() throws Exception {
        mockMvc.perform(get("/api/posts").param("sort", "hot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].likeCount").isNumber());
    }

    @Test
    void getPost_detail() throws Exception {
        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("北京协和医院骨科就诊经历分享"))
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.authorNickname").value("李患者"))
                .andExpect(jsonPath("$.likeCount").isNumber())
                .andExpect(jsonPath("$.favoriteCount").isNumber())
                .andExpect(jsonPath("$.commentCount").value(3))
                .andExpect(jsonPath("$.hospitals").isArray())
                .andExpect(jsonPath("$.specialties").isArray());
    }

    @Test
    void getPost_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/posts/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPostsByHospital() throws Exception {
        // Hospital 1 (Peking Union) has posts 1 and 4 linked
        mockMvc.perform(get("/api/posts/by-hospital/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void getPostsBySpecialty() throws Exception {
        // Specialty 1 has posts 1 and 5 linked
        mockMvc.perform(get("/api/posts/by-specialty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }
}
