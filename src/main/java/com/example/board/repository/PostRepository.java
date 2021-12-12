package com.example.board.repository;

import com.example.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    public List<Post> findPostsByMember_MemberId(Long memberId);

    public List<Post> findPostsByTitleContaining(String title);

    public List<Post> findPostsByContentContaining(String content);

    public List<Post> findPostsByDelYn(String delYn);

}
