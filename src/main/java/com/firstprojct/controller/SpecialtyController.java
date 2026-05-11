package com.firstprojct.controller;

import com.firstprojct.dto.SpecialtyDto;
import com.firstprojct.dto.SpecialtyRankingDto;
import com.firstprojct.service.RankingService;
import com.firstprojct.service.SpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final RankingService rankingService;

    public SpecialtyController(SpecialtyService specialtyService, RankingService rankingService) {
        this.specialtyService = specialtyService;
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<SpecialtyDto> listSpecialties() {
        return specialtyService.getAllSpecialties();
    }

    @GetMapping("/{id}/rankings")
    public ResponseEntity<SpecialtyRankingDto> getSpecialtyRankings(
            @PathVariable Long id,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer year) {
        SpecialtyRankingDto result = rankingService.getRankingsBySpecialty(id, year, city);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
