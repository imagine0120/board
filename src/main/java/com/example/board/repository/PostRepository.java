package com.example.board.repository;

import com.example.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    public List<Post> findPostsByMemberId(Long memberId);

    public List<Post> findPostsByTitleOrContentContaining(String search);

    public List<Post> findPostsByDelYn(String delYn);

}
