package com.project.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.entity.Reply;
import com.project.blog.service.AccountService;
import com.project.blog.service.BoardService;
import com.project.blog.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AccountService accountService;
	
	
	
	// 댓글 생성
	@PostMapping("/user/createReplyPro/{bid}")
	public String createReply(@PathVariable("bid") int bid ,Reply rp, HttpSession session){
		// 댓글 : 댓글작성글번호 + id + br_content
		rp.setBr_writer((String) session.getAttribute("sessionId"));
		replyService.create(rp);
		
		System.out.println("댓글 생성 후 : " + rp.toString()); 
		
		return "redirect:/";
		
	}
	
	// 댓글 수정
	
	// 댓글 삭제
	@GetMapping("/user/delete/{br_id}")
	public String deleteReply(@PathVariable("br_id") int br_id,Model model,HttpServletRequest request) {
		int result = replyService.ReplyDelete(br_id);
		if(result == 1) {
			model.addAttribute("msg", "게시글 삭제 완료");
			model.addAttribute("url", "/");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		}
		
		return "";
		
	}

}
