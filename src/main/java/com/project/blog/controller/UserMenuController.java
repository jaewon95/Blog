package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserMenuController {
	
	@GetMapping("/user/myPage")
	public String myPageGo() {
		return "userMenu/myPage";
	}
				  
	@GetMapping("/user/mpIconChange")
	public String mpIconInfo(@RequestParam("radioValue") String radio,
							@RequestParam("srcValue") String src) {
		
		System.out.println("radio : " + radio);
		System.out.println("src : " + src);
		
		// db에 집어넣기
		
		return "redirect:/";
	}

}
