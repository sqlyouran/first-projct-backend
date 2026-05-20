package com.firstprojct.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record CreateInquiryRequest(
        @NotBlank(message = "Name is required") String name,
        @Email(message = "Valid email is required") @NotBlank(message = "Email is required") String email,
        @NotBlank(message = "Condition summary is required") String conditionSummary,
        LocalDate preferredDate,
        Long hospitalId
) {}
