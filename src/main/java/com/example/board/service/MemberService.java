package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.dto.MemberDto;
import com.example.board.enums.Authority;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /**
     * 관리자 여부 판단하여 권한을 주는 멤버 등록 로직
     * @param requestMember
     * @param adminYn
     * @return Member
     */
    public Member join(MemberDto.Request requestMember, String adminYn){

        Member member = Member.builder()
                            .email(requestMember.getEmail())
                            .password(requestMember.getPassword())
                            .name(requestMember.getName())
                            .delYn("N")
                            .regDate(LocalDateTime.now())
                            .modDate(LocalDateTime.now())
                            .build();

        if (adminYn == "Y"){
            member.setAuthority(Authority.ROLE_ADMIN);
        } else {
            member.setAuthority(Authority.ROLE_USER);
        }

        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public MemberDto.Response updateMember(MemberDto.Info infoMember){

        Optional<Member> optMember = memberRepository.findById((infoMember.getMemberId()));
        MemberDto.Response updatedMember = new MemberDto.Response();

        if(optMember.isPresent()) {

            Member member = optMember.get();

            //modelMapper 라이브러리로 리팩토링하기
            member.setEmail(infoMember.getEmail());
            member.setPassword(infoMember.getPassword());
            member.setName(infoMember.getName());
            member.setAuthority(infoMember.getAuthority());
            member.setDelYn(infoMember.getDelYn());
            member.setModDate(infoMember.getModDate());

            memberRepository.save(member);

            updatedMember.setInfo(infoMember);
            updatedMember.setReturnCode(200);
            updatedMember.setReturnMessage("success");

        } else {
            updatedMember.setReturnCode(404);
            updatedMember.setReturnMessage("not found");
        }
        return updatedMember;
    }

    public MemberDto.Response deleteMember(Long id) {

        Optional<Member> member = memberRepository.findById(id);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (member.isPresent()){

            memberRepository.deleteById(id);

            responseMember.setReturnCode(202);
            responseMember.setReturnMessage("success");

        } else {

            responseMember.setReturnCode(404);
            responseMember.setReturnMessage("not found");

        }

        return responseMember;
    }

}
