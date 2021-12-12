package com.example.board.domain;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data @Builder
@Entity @Table(name = "Comments")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String cmtContent;

    @Column(nullable = false)
    private Long parentComment;

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

}