package com.firstprojct.controller;

import com.firstprojct.service.InteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping("/api/posts/{id}/like")
    public Map<String, Object> toggleLikePost(@PathVariable Long id, @RequestParam Long userId) {
        return interactionService.toggleLikePost(id, userId);
    }

    @PostMapping("/api/posts/{id}/favorite")
    public Map<String, Object> toggleFavoritePost(@PathVariable Long id, @RequestParam Long userId) {
        return interactionService.toggleFavoritePost(id, userId);
    }

    @PostMapping("/api/comments/{id}/like")
    public Map<String, Object> toggleLikeComment(@PathVariable Long id, @RequestParam Long userId) {
        return interactionService.toggleLikeComment(id, userId);
    }
}
