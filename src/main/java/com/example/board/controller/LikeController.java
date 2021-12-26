package com.example.board.controller;

import com.example.board.dto.LikeDto;
import com.example.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<?> createLike(LikeDto.Request requestLike, HttpServletRequest request){
        LikeDto.Response response = likeService.createLike(requestLike);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> readAllLikes(HttpServletRequest request){
        List<LikeDto.Response> response = likeService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<?> readLike(@PathVariable("likeId") Long likeId, HttpServletRequest request){
        LikeDto.Response response = likeService.findById(likeId);
        if (response.getLikeId() != null & response.getLikeId() == likeId){
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{targetId}")
    public ResponseEntity<?> readLikesByTargetId(@PathVariable("targetId") Long targetId, HttpServletRequest request){
        List<LikeDto.Response> response = likeService.findByTargetId(targetId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> readLikesByMemberId(@PathVariable("memberId") Long memberId, HttpServletRequest request){
        List<LikeDto.Response> response = likeService.findByMemberId(memberId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{likeId}")
    public ResponseEntity<?> updateLike(@PathVariable("likeId") Long likeId, LikeDto.Info infoLike, HttpServletRequest request){
        LikeDto.Response response = likeService.findById(likeId);

        if (response.getLikeId() != null & response.getLikeId() == likeId) {
            response = likeService.updateLike(infoLike);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<?> deleteLike(@PathVariable("likeId") Long likeId, HttpServletRequest request){
        LikeDto.Response response = likeService.findById(likeId);

        if (response.getLikeId() != null && response.getLikeId() == likeId) {
            response = likeService.deleteLike(likeId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
