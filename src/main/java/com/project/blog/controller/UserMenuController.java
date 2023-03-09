package com.project.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.entity.Icon;
import com.project.blog.service.IconService;

@Controller
public class UserMenuController {
	
	@Autowired
	private IconService iconService;
	
	@GetMapping("/user/myPage")
	public String myPageGo() {
		return "userMenu/myPage";
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
		
		
		
		
		// db에 집어넣기
		
		return "redirect:/";
	}

}
