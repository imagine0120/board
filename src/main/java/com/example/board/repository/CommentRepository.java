package com.example.board.repository;

import com.example.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByMemberId(Long memberId);

    List<Comment> findCommentsByPostId(Long postId);

    List<Comment> findCommentsByParentComment(Long cmtId);

}
