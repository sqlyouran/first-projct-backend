package com.firstprojct.service;

import com.firstprojct.dto.*;
import com.firstprojct.model.Hospital;
import com.firstprojct.model.SpecialtyRanking;
import com.firstprojct.repository.HospitalRepository;
import com.firstprojct.repository.SpecialtyRankingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final SpecialtyRankingRepository rankingRepository;

    public HospitalService(HospitalRepository hospitalRepository,
                           SpecialtyRankingRepository rankingRepository) {
        this.hospitalRepository = hospitalRepository;
        this.rankingRepository = rankingRepository;
    }

    public Page<HospitalSummaryDto> searchHospitals(String query, String city, Long specialtyId, Pageable pageable) {
        Page<Hospital> page;
        if (specialtyId != null) {
            page = hospitalRepository.searchBySpecialty(specialtyId, query, city, pageable);
        } else {
            page = hospitalRepository.searchHospitals(query, city, pageable);
        }
        return page.map(this::toSummaryDto);
    }

    public HospitalDetailDto getHospitalDetail(Long id) {
        return hospitalRepository.findById(id)
                .map(h -> {
                    List<SpecialtyRanking> rankings = rankingRepository.findByHospitalAndYear(h.getId(), 2023);
                    List<HospitalDetailDto.TopSpecialtyDto> topSpecialties = rankings.stream()
                            .map(r -> new HospitalDetailDto.TopSpecialtyDto(
                                    r.getSpecialty().getName(),
                                    r.getRankPosition()))
                            .toList();
                    return new HospitalDetailDto(
                            h.getId(), h.getName(), h.getNameCn(),
                            h.getCity(), h.getProvince(), h.getAddress(),
                            h.getPhone(), h.getWebsite(), h.getDescription(),
                            h.getHasInternational(), h.getImageUrl(),
                            topSpecialties);
                })
                .orElse(null);
    }

    private HospitalSummaryDto toSummaryDto(Hospital h) {
        return new HospitalSummaryDto(
                h.getId(), h.getName(), h.getNameCn(),
                h.getCity(), h.getProvince(), h.getHasInternational());
    }
}
