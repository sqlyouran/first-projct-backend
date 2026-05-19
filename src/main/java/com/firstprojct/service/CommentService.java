package com.firstprojct.service;

import com.firstprojct.dto.CommentDto;
import com.firstprojct.dto.CreateCommentRequest;
import com.firstprojct.model.Comment;
import com.firstprojct.model.MockUser;
import com.firstprojct.model.Post;
import com.firstprojct.repository.CommentRepository;
import com.firstprojct.repository.MockUserRepository;
import com.firstprojct.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MockUserRepository mockUserRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          MockUserRepository mockUserRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mockUserRepository = mockUserRepository;
    }

    @Transactional
    public CommentDto createComment(Long postId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return null; // Controller will return 404
        }

        MockUser user = mockUserRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(request.content());
        comment.setCreatedAt(LocalDateTime.now());

        if (request.parentId() != null) {
            Comment parent = commentRepository.findById(request.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
            if (parent.getParent() != null) {
                throw new IllegalArgumentException("只允许一层嵌套，不能回复一条回复");
            }
            comment.setParent(parent);
        }

        commentRepository.save(comment);

        // Update post comment count
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return toDto(comment, Collections.emptyList());
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getComments(Long postId) {
        List<Comment> topLevelComments = commentRepository.findByPostIdAndParentIsNullOrderByCreatedAtAsc(postId);

        return topLevelComments.stream().map(comment -> {
            List<Comment> replies = commentRepository.findByParentIdOrderByCreatedAtAsc(comment.getId());
            List<CommentDto> replyDtos = replies.stream()
                    .map(r -> toDto(r, Collections.emptyList()))
                    .toList();
            return toDto(comment, replyDtos);
        }).toList();
    }

    private CommentDto toDto(Comment comment, List<CommentDto> replies) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getNickname(),
                comment.getUser().getAvatarUrl(),
                comment.getLikeCount(),
                comment.getCreatedAt(),
                replies
        );
    }
}
