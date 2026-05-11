package com.firstprojct.service;

import com.firstprojct.dto.SpecialtyDto;
import com.firstprojct.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<SpecialtyDto> getAllSpecialties() {
        return specialtyRepository.findAll().stream()
                .map(s -> new SpecialtyDto(s.getId(), s.getName(), s.getNameCn(), s.getDescription(), s.getIcon()))
                .toList();
    }

    public SpecialtyDto getSpecialtyById(Long id) {
        return specialtyRepository.findById(id)
                .map(s -> new SpecialtyDto(s.getId(), s.getName(), s.getNameCn(), s.getDescription(), s.getIcon()))
                .orElse(null);
    }
}
