package com.example.board.repository;

import com.example.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findMemberByEmail(String email);

}