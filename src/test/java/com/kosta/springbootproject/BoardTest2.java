package com.kosta.springbootproject;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.kosta.springbootproject.model.Board;
import com.kosta.springbootproject.persistence.BoardRepository;

import lombok.extern.java.Log;
import oracle.net.aso.b;

@Log
@SpringBootTest
public class BoardTest2 {

	@Autowired
	BoardRepository repo;
	
//	@Test
	public void test1() {
		//writer가 "writer1"인 데이터를 검색하기
		repo.findByWriter("writer1").forEach(board -> {
			log.info(board.toString());
		});
	}
	
//	@Test
	public void test2() {
		repo.findByTitle("board title1").forEach(data -> {
			System.out.println(data);
		} );
	}
	
//	@Test
	public void test3() {
		repo.findByContent("board content1").forEach(b -> {
			log.info(b.getContent());
		});
	}
	
//	@Test
	public void test4() {
		repo.findByTitleContaining("tle2").forEach(board -> {
			System.out.println(board);
		});
	}
	
//	@Test
	public void test5() {
		repo.findByTitleEndingWith("8").forEach(board -> {
			System.out.println(board);
		});
	}
	
//	@Test
	public void test6() {
		System.out.println();
		repo.findByTitleStartingWith("게시물").forEach(action -> {
			System.out.println(action);
		});
	}
	
//	@Test
	public void test7() {
		repo.findByTitleEndingWithAndBnoGreaterThan("8", 50L).forEach(data -> {
			log.info(data.toString());
		});
	}
	
//	@Test
	public void test8() {
		repo.findByBnoGreaterThanOrderByBnoDesc(50L).forEach(action -> {
			log.info(action.toString());
		});
	}
	
//	@Test
	public void test9() {
		Timestamp regdate = new Timestamp(System.currentTimeMillis());
		repo.findByRegdateAfter(regdate).forEach(action -> {
			System.out.println(action);
		});
	}
	
//	@Test
	public void test10() {
		System.out.println("0페이지");
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardlist = repo.findByBnoGreaterThanOrderByBnoDesc(10L, paging);
		boardlist.forEach(b -> {
			System.out.println(b);
		});
		System.out.println("1페이지");
		paging = PageRequest.of(1, 5);
		List<Board> boardlist2 = repo.findByBnoGreaterThanOrderByBnoDesc(10L, paging);
		boardlist2.forEach(b -> {
			System.out.println(b);
		});
	}
	
//	@Test
	public void test11() {
		System.out.println("****0page-----------");
		Pageable paging = PageRequest.of(0, 3);
		Page<Board> result = repo.findByTitleContaining("2", paging);
		
		System.out.println("getsize:" + result.getSize());
		System.out.println("getTotalpage:" + result.getTotalPages());
		System.out.println("getTotalElements:" + result.getTotalElements());
		System.out.println("nextPageable:" + result.nextPageable());
		System.out.println("previousPageable:" + result.previousPageable());
		
		List<Board> boardlist = result.getContent();
		boardlist.forEach(b -> {
			System.out.println(b);
		});
	}
	
	@Test
	public void test12() {
		System.out.println("****0page---------");
		Pageable paging = PageRequest.of(0, 3);
		Pageable paging2 = PageRequest.of(0, 3, Direction.DESC, "bno");
		Pageable paging3 = PageRequest.of(0, 3, Sort.by("bno").descending());
		
		Page<Board> result1 = repo.findAll(paging);
		Page<Board> result2 = repo.findAll(paging2);
		Page<Board> result3 = repo.findAll(paging3);
		
		List<Board>boardlist = result1.getContent();
		boardlist.forEach(b -> {
			System.out.println(b);
		});
	}
}
