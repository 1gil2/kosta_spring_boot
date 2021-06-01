package com.kosta.springbootproject;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kosta.springbootproject.model.Board;
import com.kosta.springbootproject.model.QBoard;
import com.kosta.springbootproject.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.java.Log;
import oracle.net.aso.b;

@Log
@SpringBootTest
public class BoardTest3 {

	@Autowired
	BoardRepository repo;
	
//	@Test
	public void test1() {
		repo.findByTitle2("2").forEach(board -> {
			System.out.println(board);
		});
	}
	
//	@Test
	public void test2() {
		repo.titleAndBno("2", 100L).forEach(board -> {
			System.out.println(board);
		});
	}
	
//	@Test
	public void test3() {
		repo.findByCont("2", 150L).forEach(board -> {
			log.info(board.toString());
		});
	}
	
//	@Test
	public void test4() {
		repo.findByWriter2("2").forEach(b -> {
			System.out.println(b.getWriter());
		});
	}
	
//	@Test
	public void test5() {
		repo.findByWriter3("writer2").forEach(b -> {
			System.out.println(b);
		});
	}
	
//	@Test
	public void test6() {
		repo.selectAllEmp().forEach(arr -> {
			log.info(Arrays.toString(arr));
			System.out.println("직원이름: " + arr[1]);
		});
	}
	
//	@Test
	public void test8() {
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> result = repo.findByWriterPaging("8", paging);
		List<Board> blist = result.getContent();
		for(Board b: blist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void test9() {
		String type = "title";
		String keyword = "9";
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		if(type.equals("title")) {
			builder.and(board.title.like("%"+keyword+"%"));
		}
		builder.and(board.bno.gt(90L));
		//where title like '%?1%' and bno > 90
		
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> result = repo.findAll(builder, paging);
		List<Board> blist = result.getContent();
		blist.forEach(b -> {
			System.out.println(b);
		});
	}
}
