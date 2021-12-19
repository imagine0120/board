package com.example.board;

import com.example.board.domain.Member;
import com.example.board.dto.MemberDto;
import com.example.board.enums.Authority;
import com.example.board.repository.MemberRepository;

import com.example.board.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@EnableBatchProcessing
@SpringBootApplication
public class BoardApplication {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ModelMapper modelMapper;

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(MemberService memberService) throws Exception {
		return (String[] args) -> {
			Member member = new Member();
			member.setEmail("admin");
			member.setPassword("1234");
			member.setName("관리자");
			member.setDelYn("N");
			member.setRegDate(LocalDateTime.now());
			member.setModDate(LocalDateTime.now());

			memberService.join(modelMapper.map(member, MemberDto.Request.class), "Y");

		};
	}

}
