package com.project.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 회원가입 처리 - 아이디 중복검사
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("act_id") String id, Account act, HttpServletResponse response) throws IOException {
		
		System.out.println("야호 "+ id);
		act.setAct_id(id);
		int checkNum = service.userIdCheck(act); // 0이면 성공 
		
		String result = null;
		
		if(checkNum == 1) {
			result = "NO";
		} else {
			result = "YES";
		}
		
		return result;
	}

	// 회원가입 처리
	@PostMapping("/registerPro")
	public String registerPro(Account act, Model model) {

		int result = service.userIdCheck(act);
		
		System.out.println("컨트롤러 값 확인 : " + result);
		
		if (result == 1) {
			String msg = "회원가입 실패";
			model.addAttribute("registerFail", msg);
			return "user/register";
		}

		service.create(act);
		return "redirect:/";
	}

	// 로그인 처리
	@PostMapping("/loginPro")
	public String loginPro(Account act, Model model, HttpSession session, Criteria cri, Board board, HttpServletRequest request) {

		System.out.println(" 로그 :  " + service.login(act));

		if (service.login(act) == null) {
			System.out.println("로그인 실패");
			model.addAttribute("msg", "아이디와 비밀번호를 확인해주세요");
			model.addAttribute("url", "/");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		} else {
			session.setAttribute("sessionId", service.login(act).getAct_id()); // 세션에 로그인한 유저의 id 값 넣어주기
			session.setAttribute("loginSession",service.login(act)); 
			model.addAttribute("listAll",boardService.listAll(cri)); 
			return "redirect:/";
		}
	}

	// 로그아웃 처리
	@PostMapping("/logout")
	public String logoutPro(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	
	// oauth2 _ google
	@GetMapping("/google")
	public String naverLogin() {
		System.out.println("네이버 로그인 페이지 이동");
		return "social/google";
	}
	
	// 네이버 로그인 처리페이지
	@GetMapping("/oauth2/naver")
	public String loginPost(HttpSession session) {
		
		return "oauth2/naverResult";
	}
}















