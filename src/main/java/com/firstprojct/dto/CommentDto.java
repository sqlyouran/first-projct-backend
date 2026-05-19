package com.firstprojct.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDto(
        Long id,
        String content,
        String authorNickname,
        String authorAvatarUrl,
        Integer likeCount,
        LocalDateTime createdAt,
        List<CommentDto> replies
) {}
