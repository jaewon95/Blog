package com.project.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.entity.PageMaker;
import com.project.blog.entity.Reply;
import com.project.blog.service.AccountService;
import com.project.blog.service.BoardService;
import com.project.blog.service.ReplyService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ReplyService replyService;
	
	// 글전체조회
	@GetMapping("/")
	public String mainPage(Criteria cri,Model model) {
		
		model.addAttribute("listAll", boardService.listAll(cri));
		
		// 페이징 처리에 필요한 부분
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(" : " + pageMaker.getCri().toString()); // ?page=1 또는 perPageNum=10 이렇게 요청을 넣을 수 있음 
		
		pageMaker.setTotalCount(boardService.totalCount()); // 전체페이지의 수
		model.addAttribute("pageMaker", pageMaker);
		
		System.out.println(pageMaker.toString());
		
		return "main";
	}
	
	// 글상세조회
	@GetMapping("/user/detail/{bid}")
	public String detail(@PathVariable("bid") int bid,Board board, Model model,
						Reply rp) {
		model.addAttribute("selectOne", boardService.selectOne(bid));
		System.out.println(boardService.selectOne(bid).toString());
		
		// 댓글
		model.addAttribute("replyData", replyService.ReplyListAll(bid));
		
		System.out.println("해당 글의 댓글정보 : " + replyService.ReplyListAll(bid));
		return "board/detail";
	}
	
	// 글생성
	@GetMapping("/user/create")
	public String register() {
		return "board/create";
	}
	
	// 글생성처리
	@PostMapping("/user/createPro")
	public String registerPro(Board board) {
		
		boardService.create(board);

		return "redirect:/";
	}
	
	// 글수정
	@GetMapping("/user/update/{bid}")
	public String update(@PathVariable("bid") int bid ,Board board, Model model,HttpSession session) {
		model.addAttribute("updateData", boardService.selectOne(bid));
		return "board/update";
	}
	
	// 글수정 처리
	@PostMapping("/user/updatePro/{bid}")
	public String updatePro(@PathVariable("bid") int bid, Board board, Model model) {
		
		boardService.update(board);
		System.out.println("수정완료");
		
		return "redirect:/";
	}
	
	// 글삭제
	@GetMapping("user/delete/{bid}")
	public String deletePro(@PathVariable("bid") int bid, Board board) {
		System.out.println(board.toString());
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







