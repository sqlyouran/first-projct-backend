package com.firstprojct.controller;

import com.firstprojct.model.User;
import com.firstprojct.repository.UserRepository;
import com.firstprojct.service.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InteractionController {

    private final InteractionService interactionService;
    private final UserRepository userRepository;

    public InteractionController(InteractionService interactionService, UserRepository userRepository) {
        this.interactionService = interactionService;
        this.userRepository = userRepository;
    }

    private ResponseEntity<Long> resolveUserId(Long userId, Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            Long id = userRepository.findByEmail(email)
                    .map(User::getId)
                    .orElse(null);
            if (id != null) return ResponseEntity.ok(id);
        }
        if (userId != null) return ResponseEntity.ok(userId);
        return null;
    }

    @PostMapping("/api/posts/{id}/like")
    public ResponseEntity<?> toggleLikePost(@PathVariable Long id,
                                             @RequestParam(required = false) Long userId,
                                             Authentication authentication) {
        ResponseEntity<Long> resolved = resolveUserId(userId, authentication);
        if (resolved == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));
        return ResponseEntity.ok(interactionService.toggleLikePost(id, resolved.getBody()));
    }

    @PostMapping("/api/posts/{id}/favorite")
    public ResponseEntity<?> toggleFavoritePost(@PathVariable Long id,
                                                 @RequestParam(required = false) Long userId,
                                                 Authentication authentication) {
        ResponseEntity<Long> resolved = resolveUserId(userId, authentication);
        if (resolved == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));
        return ResponseEntity.ok(interactionService.toggleFavoritePost(id, resolved.getBody()));
    }

    @PostMapping("/api/comments/{id}/like")
    public ResponseEntity<?> toggleLikeComment(@PathVariable Long id,
                                               @RequestParam(required = false) Long userId,
                                               Authentication authentication) {
        ResponseEntity<Long> resolved = resolveUserId(userId, authentication);
        if (resolved == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));
        return ResponseEntity.ok(interactionService.toggleLikeComment(id, resolved.getBody()));
    }
}
