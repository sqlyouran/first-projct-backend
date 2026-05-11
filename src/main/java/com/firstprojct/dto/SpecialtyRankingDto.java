package com.firstprojct.dto;

import java.util.List;

public record SpecialtyRankingDto(
    SpecialtyDto specialty,
    Integer year,
    List<RankingEntryDto> rankings
) {
    public record RankingEntryDto(
        Integer rankPosition,
        String tier,
        HospitalSummaryDto hospital
    ) {}
}
