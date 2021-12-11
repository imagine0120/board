package com.example.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {

    @Id
    private Long postId;

    private String title;

    private String content;

    private String delYn;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    //FK : 멤버 id
}
