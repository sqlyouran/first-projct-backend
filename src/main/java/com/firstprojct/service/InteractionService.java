package com.firstprojct.service;

import com.firstprojct.model.Comment;
import com.firstprojct.model.Post;
import com.firstprojct.model.UserInteraction;
import com.firstprojct.repository.CommentRepository;
import com.firstprojct.repository.PostRepository;
import com.firstprojct.repository.UserInteractionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class InteractionService {

    private final UserInteractionRepository interactionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public InteractionService(UserInteractionRepository interactionRepository,
                              PostRepository postRepository,
                              CommentRepository commentRepository) {
        this.interactionRepository = interactionRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Map<String, Object> toggleLikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Optional<UserInteraction> existing = interactionRepository
                .findByUserIdAndTargetTypeAndTargetIdAndType(userId, "POST", postId, "LIKE");

        if (existing.isPresent()) {
            interactionRepository.delete(existing.get());
            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
            postRepository.save(post);
            return Map.of("liked", false, "likeCount", post.getLikeCount());
        } else {
            UserInteraction interaction = new UserInteraction();
            interaction.setUserId(userId);
            interaction.setTargetType("POST");
            interaction.setTargetId(postId);
            interaction.setType("LIKE");
            interaction.setCreatedAt(LocalDateTime.now());
            interactionRepository.save(interaction);
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
            return Map.of("liked", true, "likeCount", post.getLikeCount());
        }
    }

    @Transactional
    public Map<String, Object> toggleFavoritePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Optional<UserInteraction> existing = interactionRepository
                .findByUserIdAndTargetTypeAndTargetIdAndType(userId, "POST", postId, "FAVORITE");

        if (existing.isPresent()) {
            interactionRepository.delete(existing.get());
            post.setFavoriteCount(Math.max(0, post.getFavoriteCount() - 1));
            postRepository.save(post);
            return Map.of("favorited", false, "favoriteCount", post.getFavoriteCount());
        } else {
            UserInteraction interaction = new UserInteraction();
            interaction.setUserId(userId);
            interaction.setTargetType("POST");
            interaction.setTargetId(postId);
            interaction.setType("FAVORITE");
            interaction.setCreatedAt(LocalDateTime.now());
            interactionRepository.save(interaction);
            post.setFavoriteCount(post.getFavoriteCount() + 1);
            postRepository.save(post);
            return Map.of("favorited", true, "favoriteCount", post.getFavoriteCount());
        }
    }

    @Transactional
    public Map<String, Object> toggleLikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        Optional<UserInteraction> existing = interactionRepository
                .findByUserIdAndTargetTypeAndTargetIdAndType(userId, "COMMENT", commentId, "LIKE");

        if (existing.isPresent()) {
            interactionRepository.delete(existing.get());
            comment.setLikeCount(Math.max(0, comment.getLikeCount() - 1));
            commentRepository.save(comment);
            return Map.of("liked", false, "likeCount", comment.getLikeCount());
        } else {
            UserInteraction interaction = new UserInteraction();
            interaction.setUserId(userId);
            interaction.setTargetType("COMMENT");
            interaction.setTargetId(commentId);
            interaction.setType("LIKE");
            interaction.setCreatedAt(LocalDateTime.now());
            interactionRepository.save(interaction);
            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
            return Map.of("liked", true, "likeCount", comment.getLikeCount());
        }
    }
}
