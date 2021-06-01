package com.kosta.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.springbootproject.model.FreeBoard;
import com.kosta.springbootproject.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@GetMapping("/boardDetail")
	public void selectBoard(Model model) {
		Long bno = 333L;
		model.addAttribute("board", bservice.selectById(bno));
	}
	
	@GetMapping("/boardList2")
	public String selectAll(Model model) {
		model.addAttribute("boardlist", bservice.selectAll());
		return "boardlist";
	}
	
	
	@RequestMapping("/leaftest1")
	public void test1() {
		//default로 페이지에 연결된다. templates/test1.html로
	}
	
	@RequestMapping("/leaftest2")
	public String test2() {
		return "leaftest1";
	}

	@RequestMapping("/leaftest3")
	public String test3(Model model) {
		
		FreeBoard board = FreeBoard.builder()
				.bno(100L)
				.title("제목")
				.content("내용")
				.build();
				
		model.addAttribute("board", board);
		model.addAttribute("greeting", "안녕하세요");
		return "leaftest1";
	}

}
