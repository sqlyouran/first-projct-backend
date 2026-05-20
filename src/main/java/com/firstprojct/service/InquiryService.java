package com.firstprojct.service;

import com.firstprojct.dto.CreateInquiryRequest;
import com.firstprojct.dto.InquiryDto;
import com.firstprojct.model.Hospital;
import com.firstprojct.model.Inquiry;
import com.firstprojct.model.User;
import com.firstprojct.repository.HospitalRepository;
import com.firstprojct.repository.InquiryRepository;
import com.firstprojct.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;

    public InquiryService(InquiryRepository inquiryRepository,
                          UserRepository userRepository,
                          HospitalRepository hospitalRepository) {
        this.inquiryRepository = inquiryRepository;
        this.userRepository = userRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public InquiryDto createInquiry(CreateInquiryRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Hospital hospital = hospitalRepository.findById(request.hospitalId())
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

        Inquiry inquiry = new Inquiry();
        inquiry.setUser(user);
        inquiry.setHospital(hospital);
        inquiry.setName(request.name());
        inquiry.setEmail(request.email());
        inquiry.setConditionSummary(request.conditionSummary());
        inquiry.setPreferredDate(request.preferredDate());

        inquiry = inquiryRepository.save(inquiry);
        return toDto(inquiry);
    }

    @Transactional(readOnly = true)
    public Page<InquiryDto> getMyInquiries(Long userId, Pageable pageable) {
        return inquiryRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable)
                .map(this::toDto);
    }

    private InquiryDto toDto(Inquiry inquiry) {
        return new InquiryDto(
                inquiry.getId(),
                inquiry.getHospital().getId(),
                inquiry.getHospital().getName(),
                inquiry.getName(),
                inquiry.getEmail(),
                inquiry.getConditionSummary(),
                inquiry.getPreferredDate(),
                inquiry.getStatus(),
                inquiry.getCreatedAt()
        );
    }
}
