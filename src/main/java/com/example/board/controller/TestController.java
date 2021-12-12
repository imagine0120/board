package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.enums.Authority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    @GetMapping("/permit-all")
    public Member permitAllEndpoint(){

        Member member = Member.builder()
                        .email("hyjilim@gmail.com")
                        .password("1q2w3e4r")
                        .name("임현진")
                        .authority(Authority.ROLE_ADMIN)
                        .regDate(LocalDateTime.now())
                        .modDate(LocalDateTime.now())
                        .build();

        return member;
    }

    @GetMapping("/auth")
    public Member authEndpoint(){
        Member member = Member.builder()
                .email("test@gmail.com")
                .password("1q2w3e4r")
                .name("루엘")
                .authority(Authority.ROLE_USER)
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .build();

        return member;
    }

}
