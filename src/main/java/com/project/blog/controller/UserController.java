package com.project.blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.blog.entity.Account;
import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.entity.Icon;
import com.project.blog.service.AccountService;
import com.project.blog.service.BoardService;
import com.project.blog.service.IconService;

@Controller
public class UserController {

	@Autowired
	private AccountService service;

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private IconService iconService;

	// 회원가입 페이지 이동
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}
	
	// 회원가입 처리 - 아이디 중복검사
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("act_id") String id, Account act, HttpServletResponse response) throws IOException {
		
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
	public String registerPro(Account act, Model model,Icon icon) {

		int result = service.userIdCheck(act);
	
		
		System.out.println("컨트롤러 값 확인 : " + result);
		
		if (result == 1) {
			String msg = "회원가입 실패";
			model.addAttribute("registerFail", msg);
			return "user/register";
		}

		service.create(act);
		System.out.println("============");
		System.out.println("최근 가입한 아이디 : "+service.newUserSearch().getAct_id()); 
		String UserName = service.newUserSearch().getAct_id();
		iconService.iconSetting(UserName);
		
		return "redirect:/";
	}

	// 로그인 처리
	@PostMapping("/loginPro")
	public String loginPro(Account act, Model model, HttpSession session, Criteria cri, Board board, HttpServletRequest request) {

		
		
		if (service.login(act) == null) {
			System.out.println("로그인 실패");
			model.addAttribute("msg", "로그인 실패");
			model.addAttribute("url", "/");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		} else {
			System.out.println("로그인 한 유저 확인 :  " + service.login(act).getAct_id());
			
			// icon 
			String src = iconService.userIcon(service.login(act).getAct_id()).getIconSrc();
			
			session.setAttribute("sessionId", service.login(act).getAct_id()); // 세션에 로그인한 유저의 id 값 넣어주기
			session.setAttribute("loginUserIconSrc", src);
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















