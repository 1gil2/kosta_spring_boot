package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.DeptVO;
import com.kosta.springbootproject.persistence.DeptRepository;

@SpringBootTest
public class DeptTest {

	@Autowired
	DeptRepository deptrepo;

//	@Test
	public void insert() {
		DeptVO dept = new DeptVO();
		dept.setDepartment_name("직무이름");
		dept.setLocation_id(1);
		dept.setManager_id(1);
		deptrepo.save(dept);
	}

//	@Test
	public void insertDept() {
		for (int i = 50; i <= 55; i++) {
			DeptVO dept = new DeptVO();
			dept.setDepartment_name("직무이름" + i);
			dept.setLocation_id(i);
			dept.setManager_id(i);
			deptrepo.save(dept);
		}
	}

//	@Test
	public void selectAll() {
		deptrepo.findAll().forEach(dept -> {
			System.out.println(dept);
		});
	}

	@Test
	public void selectById() {
		DeptVO dept = deptrepo.findById(225).get();
		System.out.println(dept);
	}
	
//	@Test
	public void update() {
		deptrepo.findById(225).ifPresent(dept -> {
			dept.setDepartment_name("수정");
			dept.setLocation_id(0);
			dept.setManager_id(0);
			deptrepo.save(dept);
		});
	}
	
//	@Test
	public void delete() {
		deptrepo.deleteById(225);
	}
	
}
