package com.kosta.springbootproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_pdsboard")
@EqualsAndHashCode(of="pid")
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long pid;
	String pname;
	String pwriter;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="pdsno")
	List<PDSFile> file2;
	//@OneToMany : 기본적으로 중간테이블이 생긴다. >>안좋은 방법
	
	//@JoinColumn을 사용하면 PDSFile 테이블에 pdsno(내가 준 이름으로)칼럼이 생긴다. 
	//PDSBoard테이블에는 칼럼이 추가 되지않는다(다치가 불가하기 때문에(한건에 여러건을 넣을 수 없기때문에))
	
	//cascade = CascadeType.ALL 
	//영속전이(부모또는 자식이 변경되면 연관관계 엔티티가 모두 영향을 준다.
	
	//
	//fetch=FetchType.LAZY : 지연실행(default) >> SQL문장 outer join을 사용해서 불러와야한다.
	//fetch=FetchType.EAGER : 즉시 실행(file정보도 가져올 수 있다)
}
