package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.DeptVO;

public interface DeptRepository extends CrudRepository<DeptVO, Integer>{

}
