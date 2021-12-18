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
import java.util.ArrayList;
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

        Member member = modelMapper.map(requestMember, Member.class);
        member.setDelYn("N");

        if (adminYn == "Y"){
            member.setAuthority(Authority.ROLE_ADMIN);
        } else {
            member.setAuthority(Authority.ROLE_USER);
        }
        MemberDto.Response responseMember = modelMapper.map(memberRepository.save(member),MemberDto.Response.class);
        responseMember.setReturnCode(201);
        responseMember.setReturnMessage("success");

        return responseMember;
    }

    public MemberDto.Response findById(Long id){

        Optional<Member> optMember = memberRepository.findById(id);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (optMember.isPresent()) {

            MemberDto.Info infoMember = modelMapper.map(optMember.get(), MemberDto.Info.class);

            responseMember.setInfo(infoMember);
            responseMember.setReturnCode(200);
            responseMember.setReturnMessage("success");

        } else {

            responseMember.setReturnCode(404);
            responseMember.setReturnMessage("not found");

        }

        return responseMember;
    }

    public MemberDto.Response findByEmail(String email){

        Optional<Member> optMember = memberRepository.findByEmail(email);
        MemberDto.Response responseMember = new MemberDto.Response();

        if (optMember.isPresent()) {

            MemberDto.Info infoMember = modelMapper.map(optMember.get(), MemberDto.Info.class);

            responseMember.setInfo(infoMember);
            responseMember.setReturnCode(200);
            responseMember.setReturnMessage("success");

        } else {

            responseMember.setReturnCode(404);
            responseMember.setReturnMessage("not found");

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

            updatedMember.setInfo(infoMember);
            updatedMember.setReturnCode(201);
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
