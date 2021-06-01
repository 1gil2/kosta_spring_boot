package com.kosta.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.model2.WebBoardReply;
import com.kosta.springbootproject.service.WebBoardReplyService;

@RestController
@RequestMapping("/replies/*")
public class WebBoardReplyController {
	
	@Autowired
	WebBoardReplyService service;
	
	@GetMapping("/board/{bno}")
	public List<WebBoardReply> selectReplyByBoard(@PathVariable Long bno) {
		WebBoard board = WebBoard.builder().bno(bno).build();
		return service.selectAll(board);
	}
	
	@GetMapping("/{rno}")
	public ResponseEntity<WebBoardReply> viewReply(@PathVariable Long rno) {
		return new ResponseEntity<>(service.selectById(rno), HttpStatus.OK);
	}

	@PostMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> addReply(@PathVariable Long bno, @RequestBody WebBoardReply reply){
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.insertReply(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebBoardReply>> deleteByRno(@PathVariable Long bno, @PathVariable Long rno){
		WebBoard board = WebBoard.builder().bno(bno).build();
		service.deleteById(rno);
	
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> updateReply(@PathVariable Long bno,
			 @RequestBody WebBoardReply reply){
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.insertReply(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}

}
