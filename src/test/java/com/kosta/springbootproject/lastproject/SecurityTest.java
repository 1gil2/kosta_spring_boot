package com.kosta.springbootproject.lastproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.MemberRoleEnumType;
import com.kosta.springbootproject.security.MemberService;

@SpringBootTest
public class SecurityTest {

	@Autowired
	MemberService service;
	
//	@Test
	public void test1() {
		IntStream.range(1, 4).forEach(i -> {
			MemberDTO member = new MemberDTO();
			member.setMid("admin"+i);
			member.setMpassword("1234");
			member.setMname("관리자"+i);
			member.setMrole(MemberRoleEnumType.ADMIN);
			service.joinUser(member);
		});
		
		IntStream.range(1, 4).forEach(i -> {
			MemberDTO member = new MemberDTO();
			member.setMid("manager"+i);
			member.setMpassword("1234");
			member.setMname("매니저"+i);
			member.setMrole(MemberRoleEnumType.MANAGER);
			service.joinUser(member);
		});
		
		IntStream.range(1, 4).forEach(i -> {
			MemberDTO member = new MemberDTO();
			member.setMid("user"+i);
			member.setMpassword("1234");
			member.setMname("유저"+i);
			member.setMrole(MemberRoleEnumType.USER);
			service.joinUser(member);
		});
	}
}
