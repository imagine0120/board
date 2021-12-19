package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class LikeDto {

    @Data @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Long likeId;
        private Long targetId;
        private Long memberId;
        private String postYn;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    public static class Request {
        private Long targetId;
        private Long memberId;
        private String postYn;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long likeId;
        private Long targetId;
        private Long memberId;
        private String postYn;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

}
