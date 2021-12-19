package com.example.board.security.user;

import com.example.board.domain.Member;
import com.example.board.repository.MemberRepository;
import com.sun.xml.bind.v2.runtime.output.C14nXmlOutput;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optMember = memberRepository.findByEmail(username);

        if(optMember.isPresent()){
            Member member = optMember.get();
            CustomUserDetails user = new CustomUserDetails(member);

            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
