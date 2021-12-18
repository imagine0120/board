package com.example.board.dto;

import lombok.*;

import java.time.LocalDateTime;

public class PostDto {

    @Data @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Long postId;
        private Long memberId;
        private String title;
        private String content;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    public static class Request {
        private Long memberId;
        private String title;
        private String content;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Info info;
        private int returnCode;
        private String returnMessage;
    }

}
