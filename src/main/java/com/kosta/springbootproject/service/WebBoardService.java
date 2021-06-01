package com.kosta.springbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model2.PageVO;
import com.kosta.springbootproject.model2.WebBoard;
import com.kosta.springbootproject.persistence2.WebBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class WebBoardService {

	@Autowired
	WebBoardRepository wbrepo;

	public Page<WebBoard> selectAll(PageVO pvo) {
		Predicate p = wbrepo.makePredicate(pvo.getType(), pvo.getKeyword());
		
//		Pageable paging = PageRequest.of(1, 3);
//		PageVO pvo = new PageVO();
		Pageable paging = pvo.makePaging(pvo.getPage(), "bno");
		
		Page<WebBoard> result = wbrepo.findAll(p, paging);
		
		return result;		
	}
	
	public WebBoard selectById(Long bno) {
		return wbrepo.findById(bno).get();
	}
	
	public WebBoard insertBoard(WebBoard board) {
		return wbrepo.save(board);
	}
	
	public WebBoard updateBoard(WebBoard board) {
		return wbrepo.save(board);
	}
	
	public int deleteBoard(Long bno) {
		
		int ret = 0;
		
		try {
			wbrepo.deleteById(bno);
			ret = 1;
		} catch (Exception e) {
		}
		return ret;
	}
}
