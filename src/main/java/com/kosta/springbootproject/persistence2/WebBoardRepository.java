package com.kosta.springbootproject.persistence2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model2.QWebBoard;
import com.kosta.springbootproject.model2.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository 
	extends CrudRepository<WebBoard, Long>,
	QuerydslPredicateExecutor<WebBoard>
{	
	
	//JPQL
	@Query("select b.title, count(r) from WebBoard b left outer join b.replies r"
			+ " group by b.title order by b.title")
	public List<Object[]> getBoardReplyCount();

	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0));
		if(type==null) return builder;
		switch(type) {
		case "title":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "content":
			builder.and(board.content.like("%" + keyword + "%"));
			break;
		case "writer":
			builder.and(board.writer.like("%" + keyword + "%"));
			break;
		default:
			break;	
		}		
		return builder;
	}
}
