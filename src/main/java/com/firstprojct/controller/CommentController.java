package com.firstprojct.controller;

import com.firstprojct.dto.CommentDto;
import com.firstprojct.dto.CreateCommentRequest;
import com.firstprojct.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable Long postId,
                                           @Valid @RequestBody CreateCommentRequest request) {
        try {
            CommentDto result = commentService.createComment(postId, request);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
