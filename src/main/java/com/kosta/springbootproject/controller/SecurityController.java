package com.kosta.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.security.MemberService;

@Controller
public class SecurityController {

	@GetMapping("/auth/login")
	public void login() {
		
	}
	
	@GetMapping("/auth/joinForm")
	public void joinUser() {
		
	}
	
	@Autowired
	MemberService service;
	
	@PostMapping("/auth/joinProc")
	public String joinProc(@ModelAttribute MemberDTO member) {
		System.out.println("회원가입:" + member);
		service.joinUser(member);
		return "redirect:/auth/login";
	}
	
	@GetMapping("/admin")
	public void adminMethod() {
		
	}
	
	@GetMapping("/manager")
	public void managerMethod() {
		
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
}
