package com.kosta.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.model2.PageMaker;
import com.kosta.springbootproject.model2.PageVO;
import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.service.WebBoardService;

@Controller
public class WebBoardController {

	@Autowired
	WebBoardService service;
	
	@GetMapping("/webboard/boardlist")
	public void selectAll(Model model, PageVO page) {
		Page<WebBoard> result = service.selectAll(page);
	
		model.addAttribute("boardResult", result);
		model.addAttribute("pagevo", page);
		model.addAttribute("result", new PageMaker<>(result));
	}
	
	@GetMapping("/webboard/register")
	public void boardRegister() {
		
	}
	
	@PostMapping("/webboard/register")
	public String boardRegisterPost(WebBoard board, RedirectAttributes rttr) {
		System.out.println(board);
		WebBoard ins_board = service.insertBoard(board);
		//addFlashAttribute : 주소창에 보이지 않고 전달됨
		rttr.addFlashAttribute("resultMessage", ins_board==null?"입력실패":"입력성공");
		return "redirect:/webboard/boardlist";
		
	}
	
	@GetMapping("/webboard/boarddetail")
	public void selectById(Model model, Long bno, PageVO page) {
		model.addAttribute("board", service.selectById(bno));
		model.addAttribute("pagevo", page);
	}
	
	@GetMapping("/webboard/delete")
	public String boardDelete(Long bno) {
		int ret = service.deleteBoard(bno);
		System.out.println("삭제:" + ret);
		return "redirect:/webboard/boardlist";
	}
	
	@PostMapping("/webboard/update")
	public String boardUpdate(WebBoard board) {
		WebBoard update_board = service.updateBoard(board);
		System.out.println("수정board:" + update_board);
		return "redirect:/webboard/boardlist";
	}
	
}



