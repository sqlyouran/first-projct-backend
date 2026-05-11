package com.firstprojct.model;

import jakarta.persistence.*;

@Entity
@Table(name = "specialty_rankings")
public class SpecialtyRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(name = "rank_position", nullable = false)
    private Integer rankPosition;

    private String tier;

    @Column(name = "ranking_year", nullable = false)
    private Integer year;

    @Column(name = "source_name")
    private String source;

    public SpecialtyRanking() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public Integer getRankPosition() { return rankPosition; }
    public void setRankPosition(Integer rankPosition) { this.rankPosition = rankPosition; }

    public String getTier() { return tier; }
    public void setTier(String tier) { this.tier = tier; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
