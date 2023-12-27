package com.example.myboard.controller;

import com.example.myboard.domain.entity.Post;
import com.example.myboard.domain.request.PostCreateRequest;
import com.example.myboard.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc // 웹 애플리케이션에서 컨트롤러를 테스트할 때, 서블릿 컨테이너를 모킹하기 위해 사용.
class BoarderControllerTest {

    @Autowired
    private ObjectMapper objectMapper; // BoardCreate 객체를 Json 문자열로 만들기 위한 것.

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @DisplayName("/post 요청시 DB에 값이 저장된다.")
    @Test
    void insertPost() throws Exception {
        //given
        PostCreateRequest request = PostCreateRequest.builder()
                .title("제목입니다6.")
                .content("내용입니다6.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다6.", post.getTitle());
        assertEquals("내용입니다6.", post.getContent());

    }

}