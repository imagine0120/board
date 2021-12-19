package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.dto.CommentDto;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentDto.Response createComment(CommentDto.Request requestComment){

        Comment comment = commentRepository.save(modelMapper.map(requestComment, Comment.class));
        CommentDto.Response responseComment = modelMapper.map(comment, CommentDto.Response.class);

        return responseComment;
    }

    public CommentDto.Response findById(Long id){

        Optional<Comment> optComment = commentRepository.findById(id);
        CommentDto.Response responseComment = new CommentDto.Response();

        if (optComment.isPresent()) {
            modelMapper.map(optComment.get(), CommentDto.Response.class);
        }
        return responseComment;
    };

    public List<CommentDto.Response> findAll(){
        List<Comment> commentList =  commentRepository.findAll();
        List<CommentDto.Response> resultList = commentList.stream().map(c ->
                modelMapper.map(c,CommentDto.Response.class)).collect(Collectors.toList());

        return resultList;
    }

    public List<CommentDto.Response> findByMemberId(Long memberId){

        List<Comment> commentList =  commentRepository.findCommentsByMemberId(memberId);
        List<CommentDto.Response> resultList = commentList.stream().map(c ->
                modelMapper.map(c,CommentDto.Response.class)).collect(Collectors.toList());

        return resultList;
    };

    public List<CommentDto.Response> findByPostId(Long postId){

        List<Comment> commentList =  commentRepository.findCommentsByPostId(postId);
        List<CommentDto.Response> resultList = commentList.stream().map(c ->
                modelMapper.map(c,CommentDto.Response.class)).collect(Collectors.toList());

        return resultList;
    };

    public List<CommentDto.Response> findByParentCommentId(Long parentCmtId){

        List<Comment> commentList =  commentRepository.findCommentsByParentComment(parentCmtId);
        List<CommentDto.Response> resultList = commentList.stream().map(c ->
                modelMapper.map(c,CommentDto.Response.class)).collect(Collectors.toList());

        return resultList;
    };

    public CommentDto.Response updateComment(CommentDto.Info infoComment){

        Optional<Comment> optComment = commentRepository.findById(infoComment.getCommentId());
        CommentDto.Response responseComment = new CommentDto.Response();

        if (optComment.isPresent()) {
            infoComment.setModDate(LocalDateTime.now());
            commentRepository.save(modelMapper.map(infoComment, Comment.class));

            responseComment = modelMapper.map(infoComment, CommentDto.Response.class);
        }
        return responseComment;
    }

    public CommentDto.Response deleteComment(Long id){

        Optional<Comment> optComment = commentRepository.findById(id);
        CommentDto.Response responseComment = new CommentDto.Response();

        if (optComment.isPresent()) {
            commentRepository.deleteById(id);
            responseComment.setCommentId(id);
        }
        return responseComment;
    }

}
