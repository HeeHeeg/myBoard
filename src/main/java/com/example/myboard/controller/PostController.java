package com.example.myboard.controller;

import com.example.myboard.domain.request.PostCreateRequest;
import com.example.myboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        postService.write(postCreateRequest);
    }
}
