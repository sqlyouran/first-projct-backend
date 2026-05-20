package com.firstprojct.dto;

public record ResetPasswordRequest(String token, String newPassword) {}
