package com.example.myboard.controller;

import com.example.myboard.domain.request.PostCreateRequest;
import com.example.myboard.domain.request.PostUpdateRequest;
import com.example.myboard.domain.response.PostResponse;
import com.example.myboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/board")
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        postService.write(postCreateRequest);
    }

    @GetMapping("/posts")
    public List<PostResponse> get() {
        return postService.getAllWrite();
    }

    @PutMapping("/posts/{id}")
    public PostResponse update(
            @PathVariable Long id,
            @RequestBody @Valid PostUpdateRequest updateRequest) {

        return postService.updateWrite(id, updateRequest);
    }
}
