package com.project.blog.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.entity.PageMaker;
import com.project.blog.entity.Reply;
import com.project.blog.service.AccountService;
import com.project.blog.service.BoardService;
import com.project.blog.service.IconService;
import com.project.blog.service.ReplyService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private IconService iconService;
	
	
	
	// 글전체조회
	@GetMapping("/")
	public String mainPage(Criteria cri,Model model) {
		
		
		model.addAttribute("listAll", boardService.listAll(cri));
		model.addAttribute("iconData", iconService.iconAll());
		
		// 페이징 처리에 필요한 부분
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(boardService.totalCount()); // 전체페이지의 수
		model.addAttribute("pageMaker", pageMaker);
		
		return "main";
	}
	
	// 글상세조회
	@GetMapping("/user/detail/{bid}")
	public String detail(@PathVariable("bid") int bid,Board board, Model model,
						Reply rp) {
		model.addAttribute("selectOne", boardService.selectOne(bid));
		
		// 댓글
		model.addAttribute("replyData", replyService.ReplyListAll(bid));
		return "board/detail";
	}
	
	// 글생성
	@GetMapping("/user/create")
	public String register() {
		return "board/create";
	}
	
	// 글생성처리
	@PostMapping("/user/createPro")
	public String registerPro(Board board, @RequestParam("file") MultipartFile file) throws Exception {
		
		System.out.println("글생성처리");
		
		if(file.getOriginalFilename().isEmpty() == false) { 
			String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; // 저장경로 지정

	        UUID uuid = UUID.randomUUID(); // 파일 이름에 붙일 랜덤이름 생성

	        String filename = uuid + "_" + file.getOriginalFilename(); // 랜덤이름을 파일네임 앞에 붙인 후 _ 그리고 원래 파일이름으로 파일이름 생성

	        File saveFile = new File(projectPath, filename); // 파일을 생성해줄건데, projectPath에 담기고, name이름으로 담긴다는 의미

	        file.transferTo(saveFile); // 예외처리 필요하기에 throws를 이용, 해주기
	        
	        board.setFilename(filename);
	        board.setFilepath("/files/"+filename);
	        
			boardService.create(board, file);
		} else {
			boardService.create(board, file); 
		}

		return "redirect:/";
	}
	
	// 글수정
	@GetMapping("/user/update/{bid}")
	public String update(@PathVariable("bid") int bid,Board board, Model model) {
		model.addAttribute("updateData", boardService.selectOne(bid));
		return "board/update";
	}
	
	// 이미지 파일 삭제 메소드 - 글 수정 처리에서 사용
	public void deleteFile(String name) {
		
		// 이미지 파일 저장 경로
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
		if(name != null) {
			File file = new File(projectPath, name);
			
			if(file.exists()) { // exists 존재여부 판단. 해당경로에 파일이 있을경우 true
			
				file.delete();
				System.out.println("파일삭제 성공!!");
			}
		}
	}
	
	// 글수정 처리
	@PostMapping("/user/updatePro/{bid}")
	public String updatePro(@PathVariable("bid") int bid, Board board, Model model,@RequestParam("file") MultipartFile file) throws Exception {
		
			String path = boardService.selectOne(bid).getFilepath();
			System.out.println("path값 가져오기 위한 로그 : "+boardService.selectOne(bid).getFilepath());
			board.setFilepath(path);
			
			
			int num = file.getOriginalFilename().length();
			System.out.println(num);
	        
	        if(num != 0) { // file의 name 값이 비어있지 않다면
	        	// 기존의 이미지 삭제 메소드 실행
				deleteFile(boardService.selectOne(bid).getFilename()); 
				
	        	System.out.println("Filename이 비어있지 않음");
	        	String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; // 저장경로 지정
		        UUID uuid = UUID.randomUUID(); // 파일 이름에 붙일 랜덤이름 생성
	        
	        	String filename = uuid + "_" + file.getOriginalFilename(); // 랜덤이름을 파일네임 앞에 붙인 후 _ 그리고 원래 파일이름으로 파일이름 생성
		        File saveFile = new File(projectPath, filename); // 파일을 생성해줄건데, projectPath에 담기고, name이름으로 담긴다는 의미
		        
		        file.transferTo(saveFile); // 예외처리 필요하기에 throws를 이용, 해주기
		        board.setFilename(filename);
		        board.setFilepath("/files/"+filename);
		        
	        } else {
	        	
	        }
	        
	        boardService.update(board);
	        
		return "redirect:/";
	}
	
	// 글삭제
	@PostMapping("/user/delete/{bid}")
	public String deletePro(@PathVariable("bid") int bid, Board board) {
		board.setBdelete_yn(true);
		boardService.delete(board);
		return "redirect:/";
	}
	
	// 검색페이지 이동
	@GetMapping("/search")
	public String searchPageGo() {
		return "board/searchPage";
	}
	
	
	
}







