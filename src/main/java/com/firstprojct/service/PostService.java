package com.firstprojct.service;

import com.firstprojct.dto.CreatePostRequest;
import com.firstprojct.dto.PostDetailDto;
import com.firstprojct.dto.PostDto;
import com.firstprojct.model.*;
import com.firstprojct.repository.HospitalRepository;
import com.firstprojct.repository.MockUserRepository;
import com.firstprojct.repository.PostRepository;
import com.firstprojct.repository.SpecialtyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MockUserRepository mockUserRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecialtyRepository specialtyRepository;

    public PostService(PostRepository postRepository,
                       MockUserRepository mockUserRepository,
                       HospitalRepository hospitalRepository,
                       SpecialtyRepository specialtyRepository) {
        this.postRepository = postRepository;
        this.mockUserRepository = mockUserRepository;
        this.hospitalRepository = hospitalRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Transactional
    public PostDetailDto createPost(CreatePostRequest request) {
        MockUser user = mockUserRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post();
        post.setUser(user);
        post.setTitle(request.title());
        post.setContent(request.content());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);

        if (request.hospitalIds() != null) {
            for (Long hospitalId : request.hospitalIds()) {
                Hospital hospital = hospitalRepository.findById(hospitalId)
                        .orElseThrow(() -> new IllegalArgumentException("Hospital not found: " + hospitalId));
                PostHospitalTag tag = new PostHospitalTag();
                tag.setPost(post);
                tag.setHospital(hospital);
                post.getHospitalTags().add(tag);
            }
        }

        if (request.specialtyIds() != null) {
            for (Long specialtyId : request.specialtyIds()) {
                Specialty specialty = specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new IllegalArgumentException("Specialty not found: " + specialtyId));
                PostSpecialtyTag tag = new PostSpecialtyTag();
                tag.setPost(post);
                tag.setSpecialty(specialty);
                post.getSpecialtyTags().add(tag);
            }
        }

        postRepository.save(post);

        return toDetailDto(post);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> listPosts(String sort, Pageable pageable) {
        Page<Post> page;
        if ("hot".equalsIgnoreCase(sort)) {
            page = postRepository.findAllByHot(pageable);
        } else {
            page = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        }
        return page.map(this::toDto);
    }

    @Transactional(readOnly = true)
    public PostDetailDto getPostDetail(Long id) {
        return postRepository.findById(id)
                .map(this::toDetailDto)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> getPostsByHospital(Long hospitalId, Pageable pageable) {
        return postRepository.findByHospitalId(hospitalId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> getPostsBySpecialty(Long specialtyId, Pageable pageable) {
        return postRepository.findBySpecialtyId(specialtyId, pageable).map(this::toDto);
    }

    private PostDto toDto(Post post) {
        String preview = post.getContent();
        if (preview != null && preview.length() > 200) {
            preview = preview.substring(0, 200) + "...";
        }
        return new PostDto(
                post.getId(),
                post.getTitle(),
                preview,
                post.getUser().getNickname(),
                post.getUser().getAvatarUrl(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getCreatedAt()
        );
    }

    private PostDetailDto toDetailDto(Post post) {
        List<PostDetailDto.HospitalInfo> hospitals = post.getHospitalTags() != null
                ? post.getHospitalTags().stream()
                    .map(ht -> new PostDetailDto.HospitalInfo(ht.getHospital().getId(), ht.getHospital().getName()))
                    .toList()
                : Collections.emptyList();

        List<PostDetailDto.SpecialtyInfo> specialties = post.getSpecialtyTags() != null
                ? post.getSpecialtyTags().stream()
                    .map(st -> new PostDetailDto.SpecialtyInfo(st.getSpecialty().getId(), st.getSpecialty().getName()))
                    .toList()
                : Collections.emptyList();

        return new PostDetailDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getNickname(),
                post.getUser().getAvatarUrl(),
                post.getLikeCount(),
                post.getFavoriteCount(),
                post.getCommentCount(),
                post.getCreatedAt(),
                hospitals,
                specialties
        );
    }
}
