package com.kosta.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KostaController {
	
	@RequestMapping("/sample1")
	public void getSample1() {
		System.out.println("사용자가 sample1을 요청함...");
	}

}
