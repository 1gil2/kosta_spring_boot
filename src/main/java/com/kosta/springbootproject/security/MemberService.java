package com.kosta.springbootproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.persistence.MemberRepository;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder; // security config에서 Bean생성
	
	// 회원가입
	@Transactional
	public MemberDTO joinUser(MemberDTO member) {
		// 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		member.setMpassword(passwordEncoder.encode(member.getMpassword()));
		// member.setMrole(MemberRoleEnumType.USER);
		System.out.println(passwordEncoder.encode(member.getMpassword()));
		return memberRepository.save(member);
	}
	
	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
		UserDetails member = memberRepository.findById(mid)
				.filter(m -> m != null).map(m -> new SecurityUser(m)).get();
		return member;
	}

	
}
