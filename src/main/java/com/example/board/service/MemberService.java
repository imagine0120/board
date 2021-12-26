package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.dto.MemberDto;
import com.example.board.enums.Authority;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public MemberDto.Response join(MemberDto.Request requestMember, String adminYn){

        requestMember.setDelYn("N");

        if (adminYn == "Y"){
            requestMember.setAuthority(Authority.ROLE_ADMIN);
        } else {
            requestMember.setAuthority(Authority.ROLE_USER);
        }

        String hashedPassword = BCrypt.hashpw(requestMember.getPassword(), BCrypt.gensalt());
        requestMember.setPassword(hashedPassword);

        Member member = memberRepository.save(modelMapper.map(requestMember, Member.class));
        MemberDto.Response responseMember = modelMapper.map(member,MemberDto.Response.class);

        return responseMember;
    }

    public MemberDto.Response findById(Long id){

        Optional<Member> optMember = memberRepository.findById(id);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (optMember.isPresent()) {
            responseMember = modelMapper.map(optMember.get(), MemberDto.Response.class);
        }

        return responseMember;
    }

    public MemberDto.Response findByEmail(String email){

        Optional<Member> optMember = memberRepository.findByEmail(email);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (optMember.isPresent()) {
            responseMember = modelMapper.map(optMember.get(), MemberDto.Response.class);
        }

        return responseMember;
    }

    public List<MemberDto.Response> findAll(){

        List<MemberDto.Response> resultList = memberRepository.findAll().stream().map( m ->
            modelMapper.map(m, MemberDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public MemberDto.Response updateMember(MemberDto.Info infoMember){

        Optional<Member> optMember = memberRepository.findById((infoMember.getMemberId()));
        MemberDto.Response updatedMember = new MemberDto.Response();

        if(optMember.isPresent()) {

            infoMember.setModDate(LocalDateTime.now());
            memberRepository.save(modelMapper.map(infoMember, Member.class));

            updatedMember = modelMapper.map(infoMember, MemberDto.Response.class);
        }
        return updatedMember;
    }

    public MemberDto.Response deleteMember(Long id) {

        Optional<Member> optMember = memberRepository.findById(id);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (optMember.isPresent()){
            memberRepository.deleteById(id);
            responseMember.setMemberId(id);
        }
        return responseMember;
    }

}
