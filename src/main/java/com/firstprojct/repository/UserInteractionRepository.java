package com.firstprojct.repository;

import com.firstprojct.model.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

    Optional<UserInteraction> findByUserIdAndTargetTypeAndTargetIdAndType(
            Long userId, String targetType, Long targetId, String type);

    List<UserInteraction> findByUserIdAndTargetTypeAndType(
            Long userId, String targetType, String type);

    List<UserInteraction> findByUserIdAndType(Long userId, String type);
}
