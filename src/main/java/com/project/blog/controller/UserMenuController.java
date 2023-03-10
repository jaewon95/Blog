package com.project.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.entity.Account;
import com.project.blog.entity.Icon;
import com.project.blog.service.AccountService;
import com.project.blog.service.IconService;

@Controller
public class UserMenuController {
	
	@Autowired
	private IconService iconService;
	
	@Autowired
	private AccountService accountService;
	
	// 마이페이지 이동 - 아이콘 변경
	@GetMapping("/user/myPage")
	public String myPageGo() {
		return "userMenu/myPageIcon";
	}
	
	// 마이페이지 이동 - 비밀번호 변경
	@GetMapping("/user/myPage/Password")
	public String myPagePW() {
		return "userMenu/myPagePassword";
	}
				  
	// 아이콘 업데이트하기
	@GetMapping("/user/mpIconChange")
	public String mpIconInfo(@RequestParam("srcValue") String src, HttpSession session, Icon icon) {
		
		System.out.println("src : " + src);
		String id = (String) session.getAttribute("sessionId");
		
		icon.setAct_id(id);
		icon.setIconSrc(src);
		System.out.println(icon.toString());
		
		iconService.userIconUpdate(icon);
		
		session.setAttribute("loginUserIconSrc", icon.getIconSrc());
		
		return "redirect:/";
	}
	
	
	// 비밀번호 변경하기
	@PostMapping("/user/pwChange")
	public String pwChange(Account act, HttpSession session,  
						String newPass,	Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String loginUserId = (String) session.getAttribute("sessionId");
		String dbPW = accountService.selectPassword(loginUserId);
		
		System.out.println("1.DB 저장되어 있는 비밀번호 _" + dbPW);
		System.out.println("2.유저가 입력한 기존의 비밀번호 _" + act.getAct_pw());
		System.out.println("3.새롭게 입력한 비밀번호 _" + newPass);
		
		if( (!dbPW.equals(act.getAct_pw())) ) {
			// 1. DB에 저장되어 있는 비밀번호와 수정할때 적은 기존의 비밀번호가 같은지 비교
			model.addAttribute("msg", "변경 실패! 비밀번호를 확인해주세요");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		} else {
			// 같다면 ====>
			// 2. session 끊어주고 alert 만들어주고 메인페이지로 redirect 해주기
			
			act.setAct_pw(newPass);
			act.setAct_id(loginUserId);
			accountService.passwordUpdate(act);
			
			session.invalidate(); // 로그인 끊기
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter printwriter = response.getWriter();
			printwriter.print("<script>alert('비밀번호 변경완료! 다시 로그인 해주세요'); location.href='/'; </script>");
			printwriter.flush();
			printwriter.close();
			
		}
		
		
		
		
		
		
		
		/*
		 * if( ) { System.out.println("비밀번호 변경 완료"); // 비밀번호 변경 로직 실행해주기 } else {
		 * System.out.println("비밀번호 변경 실패"); // 다시 요청 페이지로 돌려보내기 }
		 */
		return "redirect:/";
	}
	
	

}
