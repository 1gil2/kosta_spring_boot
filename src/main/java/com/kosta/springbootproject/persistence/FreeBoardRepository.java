package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.FreeBoard;



public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{
	
	//이름 명명 규칙을 지켜야한다
	public List<FreeBoard> findByWriter(String writer);
	//public List<FreeBoard> findByBoardBno(Long bno);
	
}
