package com.example.board.controller;

import com.example.board.dto.MemberDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(MemberDto.Request requestMember, HttpServletRequest request){

        String adminYn = request.getParameter("adminYn");
        MemberDto.Response response = memberService.join(requestMember, adminYn);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> readAllMembers(HttpServletRequest request){

        List<MemberDto.Response> response = memberService.findAll();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> readMemberById(@PathVariable("memberId") Long memberId, HttpServletRequest request){

        MemberDto.Response response = memberService.findById(memberId);

        if (response.getMemberId() != null & response.getMemberId() == memberId) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> readMemberByEmail(String email, HttpServletRequest request){

        MemberDto.Response response = memberService.findByEmail(email);

        if (response.getEmail() != null & response.getEmail().equals(email)) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable("memberId") Long memberId, MemberDto.Info infoMember,
                                          HttpServletRequest request){

        MemberDto.Response response = memberService.findById(memberId);

        if (response.getMemberId() != null & response.getMemberId() == memberId) {
            response = memberService.updateMember(infoMember);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable("memberId") Long memberId, HttpServletRequest request){
        MemberDto.Response response = memberService.findById(memberId);

        if (response.getMemberId() != null & response.getMemberId() == memberId) {
            response = memberService.deleteMember(memberId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
