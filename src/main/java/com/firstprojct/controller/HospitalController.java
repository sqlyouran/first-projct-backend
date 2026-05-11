package com.firstprojct.controller;

import com.firstprojct.dto.HospitalDetailDto;
import com.firstprojct.dto.HospitalSummaryDto;
import com.firstprojct.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public Page<HospitalSummaryDto> searchHospitals(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Long specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        return hospitalService.searchHospitals(q, city, specialty, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDetailDto> getHospital(@PathVariable Long id) {
        HospitalDetailDto result = hospitalService.getHospitalDetail(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
