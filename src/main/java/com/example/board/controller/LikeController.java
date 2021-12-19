package com.example.board.controller;

import com.example.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;


}
