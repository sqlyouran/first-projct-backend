package com.firstprojct.controller;

import com.firstprojct.dto.CreateInquiryRequest;
import com.firstprojct.dto.InquiryDto;
import com.firstprojct.model.User;
import com.firstprojct.repository.UserRepository;
import com.firstprojct.service.InquiryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class InquiryController {

    private final InquiryService inquiryService;
    private final UserRepository userRepository;

    public InquiryController(InquiryService inquiryService, UserRepository userRepository) {
        this.inquiryService = inquiryService;
        this.userRepository = userRepository;
    }

    @PostMapping("/inquiries")
    public ResponseEntity<?> createInquiry(@Valid @RequestBody CreateInquiryRequest request,
                                            Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication required"));
        }
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        InquiryDto result = inquiryService.createInquiry(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/me/inquiries")
    public ResponseEntity<?> getMyInquiries(Authentication authentication,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication required"));
        }
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pageable pageable = PageRequest.of(page, Math.min(size, 100));
        Page<InquiryDto> inquiries = inquiryService.getMyInquiries(user.getId(), pageable);
        return ResponseEntity.ok(inquiries);
    }
}
