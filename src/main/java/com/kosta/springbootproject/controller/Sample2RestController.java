package com.kosta.springbootproject.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.springbootproject.model.Board;
import com.kosta.springbootproject.model.CarVO;
import com.kosta.springbootproject.persistence.BoardRepository;

@RestController
public class Sample2RestController {
	
	@Autowired
	BoardRepository boardrepo;
	
//	@RequestMapping("/boardlist")
//	public Iterable<Board> boardlist(){
//		return boardrepo.findAll();
//	}
	
	@RequestMapping("/board/{bno}")
	public Board selectById(@PathVariable Long bno) {
		return boardrepo.findById(bno).get();
	}
	

	@RequestMapping("/hello")
	public String greeting() {
		return "안녕하세요. 좋은 아침...";
	}
	
	@RequestMapping("/car")
	public CarVO getCar() {
		CarVO car = new CarVO("sm7", 4000, "삼성");
		return car;
	}
	
	@RequestMapping("/carlist")
	public List<CarVO> getCarList(){
		CarVO car1 = new CarVO("sm7", 4000, "삼성");
		CarVO car2 = new CarVO("sm3", 2000, "삼성");
		List<CarVO> carlist = new ArrayList<>();
		carlist.add(car1);
		carlist.add(car2);
		return carlist;
	}
}
