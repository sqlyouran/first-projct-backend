package com.firstprojct.repository;

import com.firstprojct.model.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("SELECT i FROM Inquiry i WHERE i.user.id = :userId ORDER BY i.createdAt DESC")
    Page<Inquiry> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);
}
