package com.firstprojct.dto;

public record AuthResponse(String accessToken, String refreshToken, UserProfileDto user) {}
