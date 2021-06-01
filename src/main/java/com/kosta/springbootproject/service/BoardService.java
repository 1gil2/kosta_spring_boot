package com.kosta.springbootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.FreeBoard;
import com.kosta.springbootproject.persistence.FreeBoardRepository;

@Service
public class BoardService {
	
	@Autowired
	FreeBoardRepository freeRepo;
	
	public FreeBoard selectById(Long bno) {
		return freeRepo.findById(bno).get();
	}
	
	public List<FreeBoard> selectAll(){
		return (List<FreeBoard>)freeRepo.findAll();
	}

}
