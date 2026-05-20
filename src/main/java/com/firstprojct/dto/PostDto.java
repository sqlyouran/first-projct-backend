package com.firstprojct.dto;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String title,
        String contentPreview,
        String authorNickname,
        String authorAvatarUrl,
        Integer likeCount,
        Integer commentCount,
        LocalDateTime createdAt,
        String type,
        String costRange,
        String outcome,
        Integer timelineDays
) {}
