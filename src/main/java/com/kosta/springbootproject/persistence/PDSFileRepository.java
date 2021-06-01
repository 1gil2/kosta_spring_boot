package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.PDSFile;



public interface PDSFileRepository extends CrudRepository<PDSFile, Long>{

}
