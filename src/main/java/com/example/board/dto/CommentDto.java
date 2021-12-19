package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDto {

    @Data @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Long commentId;
        private Long postId;
        private Long memberId;
        private String cmtContent;
        private String delYn;
        private Long parentComment;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    public static class Request {
        private Long postId;
        private Long memberId;
        private String cmtContent;
        private String delYn;
        private Long parentComment;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long commentId;
        private Long postId;
        private Long memberId;
        private String cmtContent;
        private String delYn;
        private Long parentComment;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

}
