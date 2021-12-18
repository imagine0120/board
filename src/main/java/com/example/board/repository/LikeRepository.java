package com.example.board.repository;

import com.example.board.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findLikesByTargetId(Long targetId);

    List<Like> findLikesByMemberId(Long memberId);

}
