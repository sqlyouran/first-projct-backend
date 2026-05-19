package com.firstprojct.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTopLevelComment() throws Exception {
        String json = """
                {
                    "content": "这是一条测试评论",
                    "userId": 1
                }
                """;
        mockMvc.perform(post("/api/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("这是一条测试评论"))
                .andExpect(jsonPath("$.authorNickname").value("张医生"));
    }

    @Test
    void createComment_postNotFound() throws Exception {
        String json = """
                {
                    "content": "评论内容",
                    "userId": 1
                }
                """;
        mockMvc.perform(post("/api/posts/9999/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void createComment_emptyContent() throws Exception {
        String json = """
                {
                    "content": "",
                    "userId": 1
                }
                """;
        mockMvc.perform(post("/api/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void replyToTopLevelComment() throws Exception {
        // Comment 1 is a top-level comment (parent_id is null)
        String json = """
                {
                    "content": "回复顶级评论",
                    "userId": 2,
                    "parentId": 1
                }
                """;
        mockMvc.perform(post("/api/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("回复顶级评论"));
    }

    @Test
    void replyToReply_rejected() throws Exception {
        // Comment 3 has parent_id=2, so it's a reply — replying to it should be rejected
        String json = """
                {
                    "content": "尝试回复一条回复",
                    "userId": 1,
                    "parentId": 3
                }
                """;
        mockMvc.perform(post("/api/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getComments_withNesting() throws Exception {
        // Post 1 has comments in seed: comment 1 (top), comment 2 (top), comment 3 (reply to 2)
        // Other tests may have added more comments
        mockMvc.perform(get("/api/posts/1/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))  // at least 2 top-level comments
                .andExpect(jsonPath("$[0].content").isString())
                .andExpect(jsonPath("$[0].authorNickname").isString())
                .andExpect(jsonPath("$[0].likeCount").isNumber())
                .andExpect(jsonPath("$[1].replies").isArray())
                .andExpect(jsonPath("$[1].replies", hasSize(greaterThanOrEqualTo(1))));  // Comment 2 has at least 1 reply
    }
}
