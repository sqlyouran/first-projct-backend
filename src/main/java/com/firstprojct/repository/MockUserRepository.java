package com.firstprojct.repository;

import com.firstprojct.model.MockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockUserRepository extends JpaRepository<MockUser, Long> {
}
