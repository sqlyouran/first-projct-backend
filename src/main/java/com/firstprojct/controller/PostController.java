package com.firstprojct.controller;

import com.firstprojct.dto.CreatePostRequest;
import com.firstprojct.dto.PostDetailDto;
import com.firstprojct.dto.PostDto;
import com.firstprojct.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<PostDto> listPosts(
            @RequestParam(defaultValue = "latest") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        return postService.listPosts(sort, pageable);
    }

    @PostMapping
    public ResponseEntity<PostDetailDto> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostDetailDto result = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDto> getPost(@PathVariable Long id) {
        PostDetailDto result = postService.getPostDetail(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-hospital/{hospitalId}")
    public Page<PostDto> getPostsByHospital(
            @PathVariable Long hospitalId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        return postService.getPostsByHospital(hospitalId, pageable);
    }

    @GetMapping("/by-specialty/{specialtyId}")
    public Page<PostDto> getPostsBySpecialty(
            @PathVariable Long specialtyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        return postService.getPostsBySpecialty(specialtyId, pageable);
    }
}
