package com.firstprojct.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String content;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @Column(name = "favorite_count")
    private Integer favoriteCount = 0;

    @Column(name = "comment_count")
    private Integer commentCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "type")
    private String type = "DISCUSSION";

    @Column(name = "condition_name")
    private String conditionName;

    @Column(name = "treatment_type")
    private String treatmentType;

    @Column(name = "cost_range")
    private String costRange;

    @Column(name = "timeline_days")
    private Integer timelineDays;

    @Column(name = "outcome")
    private String outcome;

    @Column(name = "nationality")
    private String nationality;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHospitalTag> hospitalTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostSpecialtyTag> specialtyTags = new ArrayList<>();

    public Post() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }

    public Integer getFavoriteCount() { return favoriteCount; }
    public void setFavoriteCount(Integer favoriteCount) { this.favoriteCount = favoriteCount; }

    public Integer getCommentCount() { return commentCount; }
    public void setCommentCount(Integer commentCount) { this.commentCount = commentCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<PostHospitalTag> getHospitalTags() { return hospitalTags; }
    public void setHospitalTags(List<PostHospitalTag> hospitalTags) { this.hospitalTags = hospitalTags; }

    public List<PostSpecialtyTag> getSpecialtyTags() { return specialtyTags; }
    public void setSpecialtyTags(List<PostSpecialtyTag> specialtyTags) { this.specialtyTags = specialtyTags; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getConditionName() { return conditionName; }
    public void setConditionName(String conditionName) { this.conditionName = conditionName; }

    public String getTreatmentType() { return treatmentType; }
    public void setTreatmentType(String treatmentType) { this.treatmentType = treatmentType; }

    public String getCostRange() { return costRange; }
    public void setCostRange(String costRange) { this.costRange = costRange; }

    public Integer getTimelineDays() { return timelineDays; }
    public void setTimelineDays(Integer timelineDays) { this.timelineDays = timelineDays; }

    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
}
