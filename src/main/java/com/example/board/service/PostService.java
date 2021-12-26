package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostDto;
import com.example.board.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

       return responsePost;
    }

    public List<PostDto.Response> findAll(){
        List<Post> postList = postRepository.findAll();
        List<PostDto.Response> resultList = postList.stream().map( p ->
            modelMapper.map(p, PostDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public PostDto.Response findById(Long id){

        Optional<Post> optPost = postRepository.findById(id);
        PostDto.Response responsePost = new PostDto.Response();

        if (optPost.isPresent()) {
            responsePost = modelMapper.map(optPost.get(), PostDto.Response.class);
        }
        return responsePost;
    };

    public List<PostDto.Response> findByMemberId(Long memberId){

        List<Post> postList =  postRepository.findPostsByMemberId(memberId);
        List<PostDto.Response> resultList = postList.stream().map(p ->
                modelMapper.map(p, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
    };

    public List<PostDto.Response> findByTitle(String title){
        List<Post> postList =  postRepository.findPostsByTitleContaining(title);
        List<PostDto.Response> resultList = postList.stream().map(p ->
                modelMapper.map(p, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
     }

    public List<PostDto.Response> findByContent(String content){
        List<Post> postList =  postRepository.findPostsByContentContaining(content);
        List<PostDto.Response> resultList = postList.stream().map(p ->
                modelMapper.map(p, PostDto.Response.class)).collect(Collectors.toList());

        return resultList;
    }

    /**
     * 제목과 내용에 특정 문자열이 포함된 Post를 찾는 메서드
     * @param search
     * @return
     */
    public List<PostDto.Response> findPostsByTitleOrContent(String search){
        List<Post> postList = postRepository.findPostsByTitleContaining(search);
        List<Long> postIdList = new ArrayList<>();

        postList.forEach(p -> {
            postIdList.add(p.getPostId());
        });

        List<Post> searchWithContent = postRepository.findPostsByContentContaining(search);
        searchWithContent.forEach( p -> {
            if(!postIdList.contains(p.getPostId())){
                postList.add(p);
            }
        });

        List<PostDto.Response> resultList = postList.stream().map( p ->
            modelMapper.map(p, PostDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public PostDto.Response updatePost(PostDto.Info infoPost){

        Optional<Post> optPost = postRepository.findById(infoPost.getPostId());
        PostDto.Response updatedPost = new PostDto.Response();

        if (optPost.isPresent()) {
            infoPost.setModDate(LocalDateTime.now());
            postRepository.save(modelMapper.map(infoPost, Post.class));

            updatedPost = modelMapper.map(infoPost, PostDto.Response.class);
        }
        return updatedPost;
    }

    public PostDto.Response deletePost(Long id){

        Optional<Post> post = postRepository.findById(id);
        PostDto.Response responsePost = new PostDto.Response();

        if (post.isPresent()) {
            postRepository.deleteById(id);
            responsePost.setPostId(id);
        }
        return responsePost;
    }

}
