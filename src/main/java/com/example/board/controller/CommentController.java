package com.example.board.controller;

import com.example.board.domain.Comment;
import com.example.board.dto.CommentDto;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createPost(CommentDto.Request requestComment, HttpServletRequest request){
        CommentDto.Response response = commentService.createComment(requestComment);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> readAllComments(HttpServletRequest request){
        List<CommentDto.Response> response = commentService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> readAllCommentsByMemberId(@PathVariable("memberId") Long memberId, HttpServletRequest request){
        List<CommentDto.Response> response = commentService.findByMemberId(memberId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> readAllCommentsByPostId(@PathVariable("postId") Long postId, HttpServletRequest request){
        List<CommentDto.Response> response = commentService.findByPostId(postId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> readComment(@PathVariable("commentId") Long commentId, HttpServletRequest request){
        CommentDto.Response response = commentService.findById(commentId);
        if (response.getCommentId() != null & response.getCommentId() == commentId) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{commentId}/parent")
    public ResponseEntity<?> readCommentsByParentComment(@PathVariable("commentId") Long commentId, HttpServletRequest request){
        List<CommentDto.Response> response = commentService.findByParentCommentId(commentId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,
                                           CommentDto.Info infoComment, HttpServletRequest request){

        CommentDto.Response response = commentService.findById(commentId);

        if (response.getCommentId() != null & response.getCommentId() == commentId) {
            response = commentService.updateComment(infoComment);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId, HttpServletRequest request){
        CommentDto.Response response = commentService.findById(commentId);
        if (response.getCommentId() != null & response.getCommentId() == commentId) {
            response = commentService.deleteComment(commentId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
