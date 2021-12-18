package com.example.board.dto;

import com.example.board.enums.Authority;
import lombok.*;

import java.time.LocalDateTime;

public class MemberDto {

    /**
     * Default DTO
     */
    @Data @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Long memberId;
        private String email;
        private String password;
        private String name;
        private Authority authority;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    /**
     * Request DTO
     */
    @Data
    public static class Request {
        private String email;
        private String password;
        private String name;
        private String delYn;
        private LocalDateTime regDate;
        private LocalDateTime modDate;
    }

    /**
     * Response DTO
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Info info;
        private int returnCode;
        private String returnMessage;
    }

}
