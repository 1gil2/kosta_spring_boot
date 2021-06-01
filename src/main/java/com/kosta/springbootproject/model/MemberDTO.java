package com.kosta.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//비교하고 싶을때 사용 괄호 작성 안하면 전체 변수가 같아야 같은거가 된다.
@EqualsAndHashCode(of = {"mid","mname"})
//엔티티 클래스임을 지정하며 테이블과 매핑된다
@Entity
//table 어노테이션 안사용하면 클래스이름이 테이블이름이 된다
@Table(name="tbl_members")
public class MemberDTO { //security에서 인증으로 사용하고자 한다.
	@Id
	String mid;
	String mname;
	String mpassword; //security를 위해 반드시 암호화되어야한다.
	//DB에 들어갈때 어떤 타입으로 들어가고 싶은지
	@Enumerated(EnumType.STRING)
	MemberRoleEnumType mrole; //인가를 위해 필요하다. 가지고있는 권한에 따라 접근가능여부
}

//인증이 먼저되고(tbl_members) 인가를 한다. (mrole칼럼으로)