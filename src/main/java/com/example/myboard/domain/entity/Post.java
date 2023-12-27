package com.example.myboard.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
//    private Long memberId; // 추후 로그인 구현시 사용

    @Builder
    private Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

//    public static Board of(String title, String content) {
//        return Board.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
