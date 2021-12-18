package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostDto;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto.Response createPost(PostDto.Request requestPost){

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

    public List<PostDto.Response> findByTitle(String title){
        List<Post> postList =  postRepository.findPostsByTitleContaining(title);
        List<PostDto.Response> resultList = postList.stream().map(m ->
                modelMapper.map(m, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
     }

    public List<PostDto.Response> findByContent(String content){
        List<Post> postList =  postRepository.findPostsByContentContaining(content);
        List<PostDto.Response> resultList = postList.stream().map(m ->
                modelMapper.map(m, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
    }

    public List<PostDto.Response> findByDelYn(String delYn){

        List<Post> postList =  postRepository.findPostsByDelYn(delYn);
        List<PostDto.Response> resultList = postList.stream().map(m ->
                modelMapper.map(m, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
    }

    public PostDto.Response updatePost(PostDto.Info infoPost){

        Optional<Post> optPost = postRepository.findById(infoPost.getPostId());
        PostDto.Response updatedPost = new PostDto.Response();

        if (optPost.isPresent()) {
            infoPost.setModDate(LocalDateTime.now());
            postRepository.save(modelMapper.map(infoPost, Post.class));

            updatedPost.setInfo(infoPost);
            updatedPost.setReturnCode(200);
            updatedPost.setReturnMessage("success");

        } else {
            updatedPost.setReturnCode(404);
            updatedPost.setReturnMessage("not found");
        }
        return updatedPost;
    }

    public PostDto.Response deletePost(Long id){

        Optional<Post> post = postRepository.findById(id);
        PostDto.Response responsePost = new PostDto.Response();

        if (post.isPresent()) {

            postRepository.deleteById(id);
            responsePost.setReturnCode(202);
            responsePost.setReturnMessage("success");

        } else {
            responsePost.setReturnCode(404);
            responsePost.setReturnMessage("not found");
        }

        return responsePost;
    }

}
