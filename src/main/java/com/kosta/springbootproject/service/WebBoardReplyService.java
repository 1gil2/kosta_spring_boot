package com.kosta.springbootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.model2.WebBoardReply;
import com.kosta.springbootproject.persistence2.WebBoardReplyRepository;

@Service
public class WebBoardReplyService {
	
	@Autowired
	WebBoardReplyRepository repo;
	
	public List<WebBoardReply> selectAll(WebBoard board) {
		return (List<WebBoardReply>)repo.findByBoard(board);
	}
	
	public WebBoardReply selectById(Long rno) {
		return repo.findById(rno).get();
	}
	
	public WebBoardReply insertReply(WebBoardReply reply) {
		return repo.save(reply);
	}

	public int deleteById(Long rno) {
		int ret = 0;
		try {
			repo.deleteById(rno);
			ret = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
}
