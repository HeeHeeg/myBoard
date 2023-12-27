package com.example.myboard.service;

import com.example.myboard.domain.entity.Post;
import com.example.myboard.domain.request.PostCreateRequest;
import com.example.myboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void write(PostCreateRequest postCreateRequest) {
        Post post = Post.builder()
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .build();
        postRepository.save(post);
    }
}
