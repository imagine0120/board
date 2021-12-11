package com.example.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    private Long commentId;

    private String cmtContent;

    private String parentComment;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    // FK : 멤버 id, 게시글 id

}