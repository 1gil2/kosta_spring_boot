package com.kosta.springbootproject;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.MemberRoleEnumType;
import com.kosta.springbootproject.model.ProfileDTO;
import com.kosta.springbootproject.persistence.MemberRepository;
import com.kosta.springbootproject.persistence.ProfileRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class MemberProfileTest {
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ProfileRepository profileRepo;
	
	//Member---------------------------------------------------
	
//	@Test
	public void insertMember() {
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = new MemberDTO();
			member.setMid("yoon"+i);
			member.setMname("윤"+i);
			member.setMpassword("1234");
			member.setMrole(MemberRoleEnumType.ADMIN);
			memberRepo.save(member);
		});
		IntStream.range(4, 7).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("kim"+i)
					.mname("김"+i)
					.mpassword("1234")
					.mrole(MemberRoleEnumType.MANAGER)
					.build();
			memberRepo.save(member);
		});
		IntStream.range(7, 10).forEach(i->{
			MemberDTO member = new MemberDTO("na"+i, "나"+i, "1234", MemberRoleEnumType.USER);
			memberRepo.save(member);
		});
	}
//	@Test
	public void findAllMember() {
		memberRepo.findAll().forEach(member->{
			log.info(member.toString());
		});
	}
	
//	@Test
	public void getMemberWithProfileCount() {
		memberRepo.getMemberWithProfileCount().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
//	@Test
	public void getMemberWithProfileCount2() {
		memberRepo.getMemberWithProfileCount().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//profile---------------------------------------------------

//	@Test
	public void insertProfile() {
		MemberDTO member = new MemberDTO();
		member.setMid("na7");
		IntStream.range(1, 4).forEach(i->{
			ProfileDTO profile = new ProfileDTO();
			profile.setFname("face"+i+".jpg");
			if(i==1) {
				profile.setCurrentYn(true);
			}else {
				profile.setCurrentYn(false);
			}
			//mid와 자동 연결된다.
			profile.setMember(member);
			profileRepo.save(profile);
		});
		
		MemberDTO member2 = new MemberDTO();
		member2.setMid("yoon1");
		IntStream.range(1, 5).forEach(i->{
			ProfileDTO profile = new ProfileDTO();
			profile.setFname("career"+i+".jpg");
			if(i==1) {
				profile.setCurrentYn(true);
			}else {
				profile.setCurrentYn(false);
			}
			profile.setMember(member2);
			profileRepo.save(profile);
		});
	}
	
//	@Test
	public void selectProfile() {
		profileRepo.findById(242L).ifPresent(prof->{
			System.out.println(prof);
			System.out.println(prof.getFname());
			System.out.println(prof.getMember());
			System.out.println(prof.getMember().getMname());
		});
	}
	
//	@Test
	public void selectProfByMember() {
		//member 번호를 알고 있을때
		//member의 모든 profile, member정보를 알아내기
		MemberDTO member = MemberDTO.builder()
				.mid("yoon1")
				.build();
		profileRepo.findByMember(member).forEach(prof->{
			log.info(prof.toString());
			log.info(prof.getMember().toString());
		});;
	}
}
