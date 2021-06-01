package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.FreeBoard;
import com.kosta.springbootproject.model.FreeBoardReply;



public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long>{
	
	public List<FreeBoardReply> findByBoard(FreeBoard board);
}
