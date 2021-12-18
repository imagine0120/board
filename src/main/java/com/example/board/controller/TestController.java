package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.domain.Post;
import com.example.board.dto.PostDto;
import com.example.board.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Spring Security 설정 테스트를 위한 컨트롤러
 */
@RequestMapping("/api/v1/test")
@RestController @RequiredArgsConstructor
public class TestController {

    private final ModelMapper modelMapper;

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

    @GetMapping("/model-mapper")
    public void modelMapperTest(){
        Post postEntity = Post.builder()
                .postId(1L)
                .memberId(100L)
                .title("제목1")
                .content("내용1")
                .delYn("N")
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .build();

        PostDto.Request postDto = modelMapper.map(postEntity, PostDto.Request.class);

        System.out.println("======================");
        //System.out.println("ID : "+postDto.getPostId());
        System.out.println("Member ID : "+postDto.getMemberId());
        System.out.println("Title : "+postDto.getTitle());
        System.out.println("Content : "+postDto.getContent());
        System.out.println("DelYn : "+postDto.getDelYn());
        System.out.println("RegDate : "+postDto.getRegDate());
        System.out.println("ModDate : "+postDto.getModDate());
        System.out.println("======================");

    }

}
