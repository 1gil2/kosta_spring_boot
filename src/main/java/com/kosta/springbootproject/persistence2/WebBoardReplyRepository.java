package com.kosta.springbootproject.persistence2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.model2.WebBoardReply;

public interface WebBoardReplyRepository 
	extends CrudRepository<WebBoardReply, Long>{
	
	public List<WebBoardReply> findByBoard(WebBoard board);

}
