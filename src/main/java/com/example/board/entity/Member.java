package com.example.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import com.example.board.enums.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Member {

    @Id
    private Long memberId;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority role;

    private String delYn;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
