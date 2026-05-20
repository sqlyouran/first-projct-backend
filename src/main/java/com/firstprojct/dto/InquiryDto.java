package com.firstprojct.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InquiryDto(
        Long id,
        Long hospitalId,
        String hospitalName,
        String name,
        String email,
        String conditionSummary,
        LocalDate preferredDate,
        String status,
        LocalDateTime createdAt
) {}
