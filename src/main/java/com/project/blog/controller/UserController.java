package com.project.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.entity.Account;
import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.entity.PageMaker;
import com.project.blog.service.AccountService;
import com.project.blog.service.BoardService;

@Controller
public class UserController {
	
	@Autowired
	private AccountService service; 
	
	@Autowired
	private BoardService boardService;
	
	// 회원가입 페이지 이동
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}
	
	// 회원가입 처리
	@PostMapping("/registerPro")
	public String registerPro(Account act, Model model) {

		int result = service.userCreateTest(act);
		System.out.println("컨트롤러 값 확인 : " + result);
		if(result == 1) {
			String msg = "회원가입 실패";
			model.addAttribute("registerFail", msg);
			return "user/register";
		}
		
		service.create(act);
		return "redirect:/";
	}
	
	// 로그인 처리
	@PostMapping("/loginPro")
	public String loginPro(Account act, Model model,HttpSession session, Criteria cri, Board board) {
		
		Account login = service.login(act);
		session.setAttribute("sessionId", service.login(act).getAct_id()); // 세션에 로그인한 유저의 id 값 넣어주기
		
		
		// 페이징 처리에 필요한 부분
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(" : " + pageMaker.getCri().toString()); // ?page=1 또는 perPageNum=10 이렇게 요청을 넣을 수 있음 
		
		pageMaker.setTotalCount(boardService.totalCount()); // 전체페이지의 수
		model.addAttribute("pageMaker", pageMaker);
		
		System.out.println(pageMaker.toString());
		
		
		if(login != null) {
			System.out.println("로그인 성공");
			session.setAttribute("loginSession", service.login(act));
			model.addAttribute("listAll", boardService.listAll(cri));
			return "redirect:/";
		} else {
			System.out.println("로그인 ㄴㄴㄴㄴㄴ");
			return "redirect:/";
		}
		
		
		
	}
	
	// 로그아웃 처리
	@PostMapping("/logout")
	public String logoutPro(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
