package com.example.board.security.user;

import com.example.board.domain.Member;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optMember = memberRepository.findMemberByEmail(username);

        if(optMember.isPresent()){

            return new CustomUserDetails(optMember.get());

        } else {

            throw new UsernameNotFoundException(username);
        }
    }
}
