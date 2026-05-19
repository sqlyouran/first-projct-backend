package com.firstprojct.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailDto(
        Long id,
        String title,
        String content,
        String authorNickname,
        String authorAvatarUrl,
        Integer likeCount,
        Integer favoriteCount,
        Integer commentCount,
        LocalDateTime createdAt,
        List<HospitalInfo> hospitals,
        List<SpecialtyInfo> specialties
) {
    public record HospitalInfo(Long id, String name) {}
    public record SpecialtyInfo(Long id, String name) {}
}
