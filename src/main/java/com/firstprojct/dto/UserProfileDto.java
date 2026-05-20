package com.firstprojct.dto;

public record UserProfileDto(Long id, String email, String nickname, String avatarUrl, String role, String createdAt) {}
