package com.example.myboard.service;

import com.example.myboard.domain.entity.Post;
import com.example.myboard.domain.request.PostCreateRequest;
import com.example.myboard.domain.response.PostResponse;
import com.example.myboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PostResponse> getAllWrite() {
        List<Post> all = postRepository.findAll();
        List<PostResponse> postResponseList = all.stream()
                .map(p -> new PostResponse(p.getTitle(), p.getContent()))
                .collect(Collectors.toList());
        return postResponseList;
    }
}
