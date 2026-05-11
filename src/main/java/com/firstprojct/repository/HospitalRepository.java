package com.firstprojct.repository;

import com.firstprojct.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("SELECT h FROM Hospital h WHERE " +
           "(:query IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(h.nameCn) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(h.city) LIKE LOWER(CONCAT('%', :query, '%'))) " +
           "AND (:city IS NULL OR LOWER(h.city) = LOWER(:city))")
    Page<Hospital> searchHospitals(@Param("query") String query,
                                   @Param("city") String city,
                                   Pageable pageable);

    @Query("SELECT DISTINCT h FROM Hospital h JOIN SpecialtyRanking sr ON sr.hospital = h " +
           "WHERE sr.specialty.id = :specialtyId " +
           "AND (:query IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(h.city) LIKE LOWER(CONCAT('%', :query, '%'))) " +
           "AND (:city IS NULL OR LOWER(h.city) = LOWER(:city))")
    Page<Hospital> searchBySpecialty(@Param("specialtyId") Long specialtyId,
                                    @Param("query") String query,
                                    @Param("city") String city,
                                    Pageable pageable);
}
