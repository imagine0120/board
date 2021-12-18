package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentDto.Response createComment(PostDto.Request requestPost){

        Post post = postRepository.save(modelMapper.map(requestPost, Post.class));

        PostDto.Response responsePost = modelMapper.map(post, PostDto.Response.class);
        responsePost.setReturnCode(201);
        responsePost.setReturnMessage("success");

        return responsePost;
    }

    public PostDto.Response findById(Long id){

        Optional<Post> optPost = postRepository.findById(id);
        PostDto.Response responsePost = new PostDto.Response();

        if (optPost.isPresent()) {
            responsePost.setInfo(modelMapper.map(optPost.get(), PostDto.Info.class));
            responsePost.setReturnCode(200);
            responsePost.setReturnMessage("success");

        } else {
            responsePost.setReturnCode(404);
            responsePost.setReturnMessage("not found");
        }

        return responsePost;
    };

    public List<PostDto.Response> findByMemberId(Long memberId){

        List<Post> postList =  postRepository.findPostsByMemberId(memberId);
        List<PostDto.Response> resultList = postList.stream().map(m ->
                modelMapper.map(m, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
    };

}
