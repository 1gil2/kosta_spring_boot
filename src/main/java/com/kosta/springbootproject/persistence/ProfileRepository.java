package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.ProfileDTO;



public interface ProfileRepository extends CrudRepository<ProfileDTO, Long> {
	
	public List<ProfileDTO> findByMember(MemberDTO member);
	
}
