package com.firstprojct.controller;

import com.firstprojct.dto.MockUserDto;
import com.firstprojct.repository.MockUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mock-users")
public class MockUserController {

    private final MockUserRepository mockUserRepository;

    public MockUserController(MockUserRepository mockUserRepository) {
        this.mockUserRepository = mockUserRepository;
    }

    @GetMapping
    public List<MockUserDto> getMockUsers() {
        return mockUserRepository.findAll().stream()
                .map(u -> new MockUserDto(u.getId(), u.getNickname(), u.getAvatarUrl()))
                .toList();
    }
}
