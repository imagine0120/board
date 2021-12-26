package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(PostDto.Request requestPost, HttpServletRequest request){

        PostDto.Response response = postService.createPost(requestPost);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> readAllPosts(HttpServletRequest request) {
        List<PostDto.Response> response = postService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> readAllPostsByMemberId(@PathVariable("memberId") Long memberId, HttpServletRequest request) {
        List<PostDto.Response> response = postService.findByMemberId(memberId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> readPostById(@PathVariable("postId") Long postId, HttpServletRequest request){

        PostDto.Response response = postService.findById(postId);

        if (response.getPostId() != null & response.getPostId() == postId) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> readPostBySearch(String search, HttpServletRequest request){
        List<PostDto.Response> response = postService.findPostsByTitleOrContent(search);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("postId") Long postId, PostDto.Info infoPost,
                                        HttpServletRequest request){

        PostDto.Response response = postService.findById(postId);

        if (response.getPostId() != null & response.getPostId() == postId) {
            response = postService.updatePost(infoPost);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId, HttpServletRequest request){
        PostDto.Response response = postService.findById(postId);

        if (response.getPostId() != null & response.getPostId() == postId) {
            response = postService.deletePost(postId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
