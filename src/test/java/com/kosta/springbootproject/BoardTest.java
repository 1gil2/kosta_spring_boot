package com.kosta.springbootproject;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Board;
import com.kosta.springbootproject.persistence.BoardRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository boardrepo;

//	@Test
	public void repoInfo() {
		Class<?> bclass = boardrepo.getClass();
		System.out.println(bclass.getName());
		Class<?>[] interf = bclass.getInterfaces();
		Stream.of(interf).forEach(inter -> {
			System.out.println(inter.getName());
		});
		System.out.println(bclass.getSuperclass().getName());
	}
	
	
//	@Test
	public void insertBoard() {
		Board b = new Board();
		b.setTitle("게시물의제목2");
		b.setContent("게시물의내용2");
		b.setWriter("게시물작성자2");
		boardrepo.save(b);
	}
	
//	@Test  //여러건 insert
	public void insertBoard2() {
		IntStream.range(1, 201).forEach(i -> {
			Board b = new Board();
			b.setTitle("board title" + i);
			b.setContent("board content" + i);
			b.setWriter("writer" + i%10);
			boardrepo.save(b);
		});
	}

//	@Test
	public void selectAll() {
		boardrepo.findAll().forEach(board -> {
			System.out.println(board);
		});
	}
	
//	@Test
	public void selectById() {
		Board board = boardrepo.findById(10L).get();
		log.info(board.toString());
	}
	
//	@Test
	public void update() {
		boardrepo.findById(10L).ifPresent(board -> {
			board.setTitle("title 수정");
			board.setContent("content수정");
			board.setWriter("gil");
			boardrepo.save(board);
		});
	}
	
//	@Test
	public void delete() {
		boardrepo.findById(10L).ifPresent(board -> {
			boardrepo.delete(board);
		});
	}
	
	@Test
	public void countBoard() {
		Long cnt = boardrepo.count();
		log.info("board건수 :" + cnt);
	}
}
