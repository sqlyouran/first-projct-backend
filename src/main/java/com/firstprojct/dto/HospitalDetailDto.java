package com.firstprojct.dto;

import java.util.List;

public record HospitalDetailDto(
    Long id,
    String name,
    String nameCn,
    String city,
    String province,
    String address,
    String phone,
    String website,
    String description,
    Boolean hasInternational,
    String imageUrl,
    List<TopSpecialtyDto> topSpecialties
) {
    public record TopSpecialtyDto(
        String specialtyName,
        Integer rankPosition
    ) {}
}
