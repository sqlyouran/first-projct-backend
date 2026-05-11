package com.firstprojct.dto;

public record HospitalSummaryDto(
    Long id,
    String name,
    String nameCn,
    String city,
    String province,
    Boolean hasInternational
) {}
