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

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public Post createPost(PostDto.Request requestPost){

        Post post = Post.builder()
                .title(requestPost.getTitle())
                .content(requestPost.getContent())
                .delYn("N")
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    };

    public List<Post> findByMemberId(Long memberId){
        return postRepository.findPostsByMemberId(memberId);
    };

    public List<Post> findByTitle(String title){
        return postRepository.findPostsByTitleContaining(title);
    }

    public List<Post> findByContent(String content){
        return postRepository.findPostsByContentContaining(content);
    }

    public List<Post> findByDelYn(String delYn){
        return postRepository.findPostsByDelYn(delYn);
    }

    public PostDto.Response updatePost(PostDto.Info infoPost){

        Optional<Post> optPost = postRepository.findById(infoPost.getPostId());
        PostDto.Response updatedPost = new PostDto.Response();

        if (optPost.isPresent()) {
            Post post = Post.builder()
                    .title(infoPost.getTitle())
                    .content(infoPost.getContent())
                    .delYn(infoPost.getDelYn())
                    .modDate(LocalDateTime.now())
                    .build();

            postRepository.save(post);

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
