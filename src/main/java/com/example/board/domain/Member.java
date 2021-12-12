package com.example.board.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import com.example.board.enums.*;

import java.time.LocalDateTime;

@Data @Builder
@Entity @Table(name = "Members")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Column(nullable = false)
    private LocalDateTime modDate;

}
