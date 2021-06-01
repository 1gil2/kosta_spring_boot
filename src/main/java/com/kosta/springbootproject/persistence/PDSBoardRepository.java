package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.PDSBoard;


public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	
	//fetch=FetchType.LAZY일때 file정보를 불러오고 싶을때 
	//PDSFile은 file2가 찾는다.-----test3,test4
	@Query(value = "select b.pid, count(f)"
			+ " from PDSBoard b left outer join b.file2 f"
			+ " group by b.pid")
	public List<Object[]> getBoardWithFileCount();
	
	@Query(value = "select b.pid, b.pname, b.pwriter, f.fno, f.pdsfilename"
			+ " from PDSBoard b left outer join b.file2 f")
	public List<Object[]> getBoardWithFileCount2();
	
	
	//첨부파일을 수정하기 ----test5
	//@Query는 select만 가능(@Modifying안하면 수정 원래는 불가능함)
	@Modifying
	@Query("update from PDSFile f set f.pdsfilename=?2 where f.fno=?1")
	public int updatePDSFile(Long fno, String filename);
}
