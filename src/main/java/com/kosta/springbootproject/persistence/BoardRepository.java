package com.kosta.springbootproject.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kosta.springbootproject.model.Board;


//
public interface BoardRepository 
	extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	//기본 findAll, findById, save, count, exists
	
	//추가
	public List<Board> findByWriter(String writer);
	public List<Board> findByTitle(String title);
	public List<Board> findByContent(String content);
	
	public List<Board> findByTitleContaining(String title);
	public List<Board> findByTitleStartingWith(String title);
	public List<Board> findByTitleEndingWith(String title);
	
	public List<Board> findByTitleEndingWithAndBnoGreaterThan(String title, Long bno);

	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno); 
	
	public List<Board> findByRegdateAfter(Timestamp regdate);
	
	//오버로딩
	//페이지기능추가
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging); 

	public Page<Board> findByTitleContaining(String title, Pageable paging);

	public Page<Board> findAll(Pageable paging);
	
	//JPQL(Java Persistence Query Language)
	//함수이름은 내가 정의한다. (규칙없음)
	@Query("select b from Board b where b.title like %?1% order by b.bno desc")
	public List<Board> findByTitle2(String title);
	
	@Query("select b from Board b where b.title like %?1% and b.bno > ?2 order by b.bno desc")
	public List<Board> titleAndBno(String title, Long bno);
	
	@Query("select b from Board b where b.content like %:content% and b.bno > :bb order by b.bno")
	public List<Board> findByCont(@Param("content") String cc, @Param("bb") Long bno); 

	@Query("select b from #{#entityName} b where b.writer like %?1%")
	public List<Board> findByWriter2(String writer);
	
	//DB에 국한된 SQL문 사용하기
	@Query(value = "select * from tbl_boards where writer = ?1 order by 1",
			nativeQuery = true)
	public List<Board> findByWriter3(String writer); 
	
	@Query(value = "select d.department_name, e.first_name, e.salary, e.hire_date from employees e join departments d using (department_id)",
			nativeQuery = true)
	public List<Object[]> selectAllEmp();
	
	
	//Query와 paging
	@Query("select b from #{#entityName} b where b.writer like %?1% ")
	public Page<Board> findByWriterPaging(String writer, Pageable paging); 
}
