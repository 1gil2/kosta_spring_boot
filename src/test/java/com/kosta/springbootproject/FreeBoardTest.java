package com.kosta.springbootproject;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.springbootproject.model.FreeBoard;
import com.kosta.springbootproject.model.FreeBoardReply;
import com.kosta.springbootproject.persistence.FreeBoardReplyRepository;
import com.kosta.springbootproject.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@Commit
@Log
@SpringBootTest
public class FreeBoardTest {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
//	@Test
	public void test1() {
		//board넣기
		IntStream.range(1, 201).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("FreeBoard Title"+i)
					.content("FreeBoard Content"+i)
					.writer("writer"+i%10)
					.build();
			boardRepo.save(board);
		});
	}
	
//	@Test
	public void test2() {
 		boardRepo.findAll().forEach(b->{
			System.out.println(b); 		//board가 reply참조 reply가 board를 참조해서 ToString이 무한반복된다.
										//ToString에 exclude넣어줘야함
		});
	}
	
//	@Test
	//작성자가 "writer9"인 board들 출력
	public void test3() {
		boardRepo.findByWriter("writer9").forEach(board->{
			log.info(board.toString());
		});
	}
	//fetch=FetchType.LAZY(지연실행인 경우는 반드시 사용)
	//@Transactional	//fetch = FetchType.EAGER(즉시실행인 경우는 미사용)>> @commit 써줘야함
	//@Test
//	public void test4() {
//		//reply넣기
//		boardRepo.findById(800L).ifPresent(b->{
//			List<FreeBoardReply> rlist = b.getReplies();
//			//5번 돌면서 reply생성
//			IntStream.range(7, 10).forEach(i->{
//				FreeBoardReply reply = FreeBoardReply.builder()
//						.reply("댓들작성800..내용")
//						.replyer("댓들작성800..작성자"+i)
//						.board(b)
//						.build();
//				rlist.add(reply);
//			});
//			boardRepo.save(b);
//		});
//	}
	
	//@Transactional
//	@Test
	public void test5() {
		boardRepo.findById(777L).ifPresent(b->{
			System.out.println(b.getReplies().size()+"개의 댓글");	//LAZY는 Transactional사용해줘야한다.
			b.getReplies().forEach(reply->{
				System.out.println(reply.getReplyer());	//주의! ToString의 exclude적용해줘야함(안그러면 무한루프)
			});
		});
	}
	
//	@Test
	public void test6() {
		//board id로 reply 찾기
		FreeBoard board = FreeBoard.builder()
				.bno(777L)
				.build();
		replyRepo.findByBoard(board).forEach(b->{
			System.out.println(b);
		});;
		
		
	}
}
