package com.firstprojct.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CreatePostRequest(
        @NotBlank String title,
        @NotBlank String content,
        Long userId,
        List<Long> hospitalIds,
        List<Long> specialtyIds
) {}
