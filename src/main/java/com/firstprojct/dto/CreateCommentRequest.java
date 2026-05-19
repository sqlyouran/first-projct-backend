package com.firstprojct.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
        @NotBlank String content,
        Long userId,
        Long parentId
) {}
