package com.kosta.springbootproject.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.MemberDTO;



public interface MemberRepository extends CrudRepository<MemberDTO, String>{
	
	//DB문법
	@Query(value="select m.mid, count(p.fno)"
			+ " from tbl_members m left outer join tbl_profile p on (m.mid = p.member_mid)"
			+ " group by m.mid", nativeQuery = true)
	public List<Object[]> getMemberWithProfileCount();
	
	//JPQL문법 : entity이름에 주의한다.
	@Query(value = "select m.mid, count(p)"
			+ " from MemberDTO m left outer join ProfileDTO p on (m.mid = p.member)"
			+ " group by m.mid")
	public List<Object[]> getMemberWithProfileCount2();
	
	
}
