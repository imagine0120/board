package com.example.board.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data @Builder
@Entity @Table(name = "Likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    // 게시글 혹은 댓글 id (postYn -> 게시글 여부 판별)
    @Column(nullable = false)
    private Long targetId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private String postYn;

    @Column(nullable = false)
    private LocalDateTime regDate;
}
