package com.firstprojct.service;

import com.firstprojct.dto.HospitalSummaryDto;
import com.firstprojct.dto.SpecialtyDto;
import com.firstprojct.dto.SpecialtyRankingDto;
import com.firstprojct.model.Hospital;
import com.firstprojct.model.SpecialtyRanking;
import com.firstprojct.repository.SpecialtyRankingRepository;
import com.firstprojct.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final SpecialtyRankingRepository rankingRepository;
    private final SpecialtyRepository specialtyRepository;

    public RankingService(SpecialtyRankingRepository rankingRepository,
                          SpecialtyRepository specialtyRepository) {
        this.rankingRepository = rankingRepository;
        this.specialtyRepository = specialtyRepository;
    }

    public SpecialtyRankingDto getRankingsBySpecialty(Long specialtyId, Integer year, String city) {
        int rankingYear = (year != null) ? year : 2023;

        return specialtyRepository.findById(specialtyId)
                .map(specialty -> {
                    SpecialtyDto specialtyDto = new SpecialtyDto(
                            specialty.getId(), specialty.getName(),
                            specialty.getNameCn(), specialty.getDescription(), specialty.getIcon());

                    List<SpecialtyRanking> rankings = rankingRepository
                            .findBySpecialtyAndYear(specialtyId, rankingYear, city);

                    List<SpecialtyRankingDto.RankingEntryDto> entries = rankings.stream()
                            .map(r -> {
                                Hospital h = r.getHospital();
                                HospitalSummaryDto hospitalDto = new HospitalSummaryDto(
                                        h.getId(), h.getName(), h.getNameCn(),
                                        h.getCity(), h.getProvince(), h.getHasInternational());
                                return new SpecialtyRankingDto.RankingEntryDto(
                                        r.getRankPosition(), r.getTier(), hospitalDto);
                            })
                            .toList();

                    return new SpecialtyRankingDto(specialtyDto, rankingYear, entries);
                })
                .orElse(null);
    }
}
