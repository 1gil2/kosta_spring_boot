package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.CarVO;

import lombok.extern.java.Log;


//JUNIT
@Log
@SpringBootTest
class SpringbootfirstApplicationTests {

	@Test
	public void lombokTest2() {
		CarVO car1 = new CarVO().builder()
				.model("페라리").
				price(9999).
				company("외국").build();
		
		log.info(car1.toString());
	}
	
	
//	@Test
	public void lombokTest1() {
		CarVO car1 = new CarVO();
		CarVO car2 = new CarVO("sm5", 2000, "현대");
		CarVO car3 = new CarVO("그랜져", "기아");
		CarVO car4 = new CarVO("그랜져", "기아");
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
		System.out.println(car2.getModel());
		System.out.println(car1.equals(car2));
		System.out.println(car3.equals(car4));
	}
	
	@Test
	void contextLoads() {
	}

}
