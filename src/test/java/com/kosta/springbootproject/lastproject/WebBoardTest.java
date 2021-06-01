package com.kosta.springbootproject.lastproject;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import com.kosta.springbootproject.model2.PageVO;
import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.model2.WebBoardReply;
import com.kosta.springbootproject.persistence2.WebBoardRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Commit
@Log
@SpringBootTest
public class WebBoardTest {

	@Autowired
	WebBoardRepository wbrepo;

//	@Test
	public void conditionRetrieve() {
		Predicate p = wbrepo.makePredicate(null, null);
		
//		Pageable paging = PageRequest.of(1, 3);
		PageVO pvo = new PageVO();
		Pageable paging = pvo.makePaging(0, "bno");
		
		Page<WebBoard> result = wbrepo.findAll(p, paging);
		List<WebBoard> boardlist = result.getContent();
		
		boardlist.forEach(b -> {
			System.out.println(b);
		});
		System.out.println("한페이지사이즈 : " + result.getSize());
		System.out.println("전체페이지 : " + result.getTotalPages());
	}
	
//	@Test
	public void boardReplyCount2() {
		wbrepo.getBoardReplyCount().forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});
	}
	
	@Transactional
//	@Test
	public void boardReplyCount() {
		wbrepo.findById(461L).ifPresent(b -> {
			System.out.println(b.getReplies().size());
		});
	}
	
	@Transactional
//	@Test
	public void insertReply() {
		wbrepo.findById(462L).ifPresent(b -> {
			List<WebBoardReply> replies = b.getReplies();
			b.setTitle("title수정합니다2.");
			IntStream.range(1, 4).forEach(i -> {
				WebBoardReply wreply = WebBoardReply.builder()
						.reply("댓글..."+i)
						.replyer("writer.."+i)
						.board(b)
						.build();
				replies.add(wreply);
			});
			wbrepo.save(b);
		});
	}
	
//	@Test
	public void insertBoardReply() {
		IntStream.range(125, 155).forEach(i -> {
			WebBoard board = WebBoard.builder()
					.title("title.."+i)
					.content("content.."+i)
					.writer("writer.."+i%3)
					.build();
			wbrepo.save(board);
		});
	}
	
}
