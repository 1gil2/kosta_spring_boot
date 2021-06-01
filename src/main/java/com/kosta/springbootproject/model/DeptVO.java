package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity // JPA가 관리함을 의미
@Table(name="tbl_dept",
uniqueConstraints = {@UniqueConstraint(name="unique2",
					columnNames = {"manager_id", "location_id"})}
		)
public class DeptVO {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	int department_id;
	@Column(name = "dept_name", unique = true, nullable = false, length = 30)
	String department_name;
	@Column(name = "manager_id", nullable = true)
	int manager_id;
	int location_id;
	
}
