package com.example.board.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data @Builder
@Entity @Table(name = "Posts")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

}
