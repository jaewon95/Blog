package com.project.blog.controller;

import javax.servlet.http.HttpServletRequest;
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

}
