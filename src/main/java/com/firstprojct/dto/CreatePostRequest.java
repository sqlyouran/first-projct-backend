package com.firstprojct.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CreatePostRequest(
        @NotBlank String title,
        @NotBlank String content,
        Long userId,
        List<Long> hospitalIds,
        List<Long> specialtyIds,
        String type,
        String conditionName,
        String treatmentType,
        String costRange,
        Integer timelineDays,
        String outcome,
        String nationality
) {}
