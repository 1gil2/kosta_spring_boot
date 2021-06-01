package com.kosta.springbootproject.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.kosta.springbootproject.model2.WebBoardReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_freeboards")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	
	String title;
	String writer;
	String content;
	
	@CreationTimestamp
	Timestamp regDate;
	@UpdateTimestamp
	Timestamp updateDate;
	
	//board가 reply에 매여있다(댓글이 있는 상태에서 게시판 삭제 불가능)
	//mappedBy때문에 @JoinColumn안해도 중간 테이블이 생기지 않고 boardReply에 칼럼(Board_bno)이 추가된다.
	//@JsonIgnore
	@OneToMany(mappedBy = "board",
			 cascade = CascadeType.ALL
			 //,fetch = FetchType.EAGER
			 )
	List<WebBoardReply> replies;
}
