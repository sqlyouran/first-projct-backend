package com.firstprojct.repository;

import com.firstprojct.model.SpecialtyRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialtyRankingRepository extends JpaRepository<SpecialtyRanking, Long> {

    @Query("SELECT sr FROM SpecialtyRanking sr " +
           "JOIN FETCH sr.hospital h " +
           "WHERE sr.specialty.id = :specialtyId " +
           "AND sr.year = :year " +
           "AND (:city IS NULL OR LOWER(h.city) = LOWER(:city)) " +
           "ORDER BY sr.rankPosition ASC")
    List<SpecialtyRanking> findBySpecialtyAndYear(@Param("specialtyId") Long specialtyId,
                                                   @Param("year") Integer year,
                                                   @Param("city") String city);

    @Query("SELECT sr FROM SpecialtyRanking sr " +
           "JOIN FETCH sr.specialty " +
           "WHERE sr.hospital.id = :hospitalId " +
           "AND sr.year = :year " +
           "ORDER BY sr.rankPosition ASC")
    List<SpecialtyRanking> findByHospitalAndYear(@Param("hospitalId") Long hospitalId,
                                                  @Param("year") Integer year);
}
