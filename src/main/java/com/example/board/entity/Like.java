package com.example.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Like {

    @Id
    private Long likeId;

    // 게시글 혹은 댓글 id (postYn -> 게시글 여부 판별)
    private Long targetId;

    private String postYn;

    private LocalDateTime regDate;
}
