package com.project.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.entity.Account;
import com.project.blog.mapper.AccountMapper;
import com.project.blog.service.AccountService;
import com.project.blog.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AdminService adminService;
	
	// 유저 관리 페이지 이동
	@GetMapping("/admin/userManage")
	public String userManagePage(Model model, Account act) {
		
		model.addAttribute("actData", accountService.userAll()); 
		
		return "admin/userManage";
	}
	
	// 블랙리스트 해제
	@PostMapping("/admin/removeBlacklist/{act_num}")
	public String removeB(@PathVariable("act_num") int act_num, Account act, Model model, HttpServletRequest request) {
		
		act.setAct_num(act_num);
		int result = adminService.blackListRemove(act);
		
		if(result == 1) {
			model.addAttribute("msg", "블랙리스트 해제");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		}
		
		
		return "";
	}
	
	// 블랙리스트 추가
	@PostMapping("/admin/addBlacklist/{act_num}")
	public String addB(@PathVariable("act_num") int act_num, Account act, Model model, HttpServletRequest request) {
		
		act.setAct_num(act_num);
		int result = adminService.blackListAdd(act);
		
		if(result == 1) {
			model.addAttribute("msg", "블랙리스트 지정");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		}
		
		return "";
	}
}
