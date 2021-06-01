package com.kosta.springbootproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.FetchType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.springbootproject.model.PDSBoard;
import com.kosta.springbootproject.model.PDSFile;
import com.kosta.springbootproject.persistence.PDSBoardRepository;
import com.kosta.springbootproject.persistence.PDSFileRepository;

import lombok.extern.java.Log;

@Commit
@Log
@SpringBootTest
public class PDSBoardTest {
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	@Autowired
	PDSFileRepository fileRepo;
	
	//@Test
	public void test1() {
		//pdsboard로 pdsboard, pdsfile entity를 insert
		
		IntStream.range(1, 6).forEach(i->{
			PDSBoard board = new PDSBoard();
			board.setPname("게시글"+i);
			board.setPwriter("작성자"+i);
			
			List<PDSFile> filelist = new ArrayList<>();
			IntStream.range(1, 4).forEach(j->{
				PDSFile file = new PDSFile();
				file.setPdsfilename("파일"+j);
				filelist.add(file);
			});
			board.setFile2(filelist);
			boardRepo.save(board);
		});
	}
	
	//@Test
	public void test2() {
		//board pid로 File찾기
		boardRepo.findById(305L).ifPresent(b->{
			System.out.println(b.getPname());
			System.out.println(b.getPwriter());
			System.out.println(b);	//실패 >> lazy하게 동작 fetch=FetchType.EAGER로 바꾸면출력가능(PDSBoard.java)
			System.out.println(b.getFile2()); //실패 >> lazy하게 동작
			System.out.println(b.getFile2().size()); //실패 >> lazy하게 동작
		});
	}
	
	//fetch=FetchType.LAZY 일때 file불러오기--------------------------------------
	//@Test
	public void test3() {
		boardRepo.getBoardWithFileCount().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	//@Test
	public void test4() {
		boardRepo.getBoardWithFileCount2().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//첨부파일을 수정하기-------------------------------------------
	//@Transactional		//@Query가 DML인 경우(insert, delete, update), 자동(rollback)
						//자동 rollback하고 싶지않으면 클래스레벨에 @Commit 추가
	//@Test
	public void test5() {
		//PDSFileRepository안만들고 PDSBoard에서 파일수정하기
		int result = boardRepo.updatePDSFile(300L, "파일이름수정함");
		System.out.println(result+"건 수정");
	}
	
	//@Test
	public void test6() {
		//PDSFileRepository만들어서 수정
		fileRepo.findById(300L).ifPresent(f->{
			f.setPdsfilename("전통적인 방법으로 수정");
			fileRepo.save(f);
		});
	}
	
	//PDSBoard수정하면서 PDSFile추가------------------------------------------------------
	//@Test
	public void test7() {
		boardRepo.findById(305L).ifPresent(b->{
			b.setPname("보드정보수정하고파일추가하기");
			b.setPwriter("테스트7");
			List<PDSFile> flist = b.getFile2();		// fetch=FetchType.EAGER되어있어야한다.
			PDSFile f = PDSFile.builder()
					.pdsfilename("board로file추가")
					.build();
			flist.add(f);
			boardRepo.save(b);
		});
	}
}
