package com.firstprojct.repository;

import com.firstprojct.model.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

    Optional<UserInteraction> findByUserIdAndTargetTypeAndTargetIdAndType(
            Long userId, String targetType, Long targetId, String type);
}
