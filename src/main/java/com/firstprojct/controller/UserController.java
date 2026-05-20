package com.firstprojct.controller;

import com.firstprojct.dto.*;
import com.firstprojct.model.Post;
import com.firstprojct.model.User;
import com.firstprojct.model.UserInteraction;
import com.firstprojct.repository.PostRepository;
import com.firstprojct.repository.UserInteractionRepository;
import com.firstprojct.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserInteractionRepository interactionRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PostRepository postRepository,
                          UserInteractionRepository interactionRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.interactionRepository = interactionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User getCurrentUser(Authentication authentication) {
        if (authentication == null) return null;
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));
        return ResponseEntity.ok(toProfileDto(user));
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request,
                                           Authentication authentication) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));

        if (request.nickname() != null && !request.nickname().isBlank()) {
            user.setNickname(request.nickname());
        }
        if (request.avatarUrl() != null) {
            user.setAvatarUrl(request.avatarUrl());
        }
        userRepository.save(user);
        return ResponseEntity.ok(toProfileDto(user));
    }

    @PutMapping("/me/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            Authentication authentication) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Current password is incorrect"));
        }
        if (request.newPassword() == null || request.newPassword().length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("error", "New password must be at least 6 characters"));
        }
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
    }

    @GetMapping("/me/posts")
    public ResponseEntity<?> getMyPosts(Authentication authentication,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        Page<Post> postPage = postRepository.findByUserIdOrderByCreatedAtDesc(user.getId(), pageable);
        Page<PostDto> result = postPage.map(this::toPostDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/favorites")
    public ResponseEntity<?> getMyFavorites(Authentication authentication,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));

        List<UserInteraction> favorites = interactionRepository
                .findByUserIdAndTargetTypeAndType(user.getId(), "POST", "FAVORITE");

        List<Long> postIds = favorites.stream()
                .map(UserInteraction::getTargetId)
                .collect(Collectors.toList());

        List<PostDto> posts = postRepository.findAllById(postIds).stream()
                .map(this::toPostDto)
                .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, posts.size());
        List<PostDto> pageContent = start >= posts.size() ? List.of() : posts.subList(start, end);
        Page<PostDto> result = new PageImpl<>(pageContent, PageRequest.of(page, size), posts.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/likes")
    public ResponseEntity<?> getMyLikes(Authentication authentication,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        User user = getCurrentUser(authentication);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Authentication required"));

        List<UserInteraction> likes = interactionRepository
                .findByUserIdAndTargetTypeAndType(user.getId(), "POST", "LIKE");

        List<Long> postIds = likes.stream()
                .map(UserInteraction::getTargetId)
                .collect(Collectors.toList());

        List<PostDto> posts = postRepository.findAllById(postIds).stream()
                .map(this::toPostDto)
                .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, posts.size());
        List<PostDto> pageContent = start >= posts.size() ? List.of() : posts.subList(start, end);
        Page<PostDto> result = new PageImpl<>(pageContent, PageRequest.of(page, size), posts.size());
        return ResponseEntity.ok(result);
    }

    private UserProfileDto toProfileDto(User user) {
        return new UserProfileDto(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getAvatarUrl(),
                user.getRole().name(),
                user.getCreatedAt() != null ? user.getCreatedAt().toString() : null
        );
    }

    private PostDto toPostDto(Post post) {
        String preview = post.getContent();
        if (preview != null && preview.length() > 200) {
            preview = preview.substring(0, 200) + "...";
        }
        return new PostDto(
                post.getId(),
                post.getTitle(),
                preview,
                post.getUser().getNickname(),
                post.getUser().getAvatarUrl(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getCreatedAt()
        );
    }
}
