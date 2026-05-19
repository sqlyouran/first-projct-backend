package com.firstprojct.repository;

import com.firstprojct.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM Post p ORDER BY (p.likeCount + p.commentCount) DESC")
    Page<Post> findAllByHot(Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.hospitalTags ht WHERE ht.hospital.id = :hospitalId ORDER BY p.createdAt DESC")
    Page<Post> findByHospitalId(@Param("hospitalId") Long hospitalId, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.specialtyTags st WHERE st.specialty.id = :specialtyId ORDER BY p.createdAt DESC")
    Page<Post> findBySpecialtyId(@Param("specialtyId") Long specialtyId, Pageable pageable);
}
