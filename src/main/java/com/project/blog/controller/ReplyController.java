package com.project.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.entity.Reply;
import com.project.blog.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	

	
	// 댓글 생성
	@PostMapping("/user/createReplyPro/{bid}")
	public String createReply(@PathVariable("bid") int bid ,Reply rp, HttpSession session, Model model, HttpServletRequest request){
		// 댓글 : 댓글작성글번호 + id + br_content
		rp.setBr_writer((String) session.getAttribute("sessionId"));
		int result = replyService.create(rp);
		
		System.out.println("댓글 생성 후 : " + rp.toString()); 
		
		if(result == 1) {
			model.addAttribute("msg", "댓글 작성 완료");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		}
		
		return "redirect:/";
		
	}


	// 댓글 수정
	@PostMapping("/user/ReplyPro/{br_id}")
	public String replyUpdatePro(@PathVariable("br_id") int br_id, String br_content ,Reply reply, Model model, HttpServletRequest request) {
		
		System.out.println("=======================");
		System.out.println("");
		System.out.println(br_content);
		System.out.println(reply.toString());
		System.out.println("");
		System.out.println("=======================");
		
		
		int result = replyService.ReplyUpdate(reply);
		
		if(result == 1) {
			model.addAttribute("msg", "댓글 수정 완료");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		} 
		
		return "alert";
	}
	
	
	
	
	// 댓글 삭제
	@PostMapping("/user/reDelete/{br_id}")
	public String deleteReply(@PathVariable("br_id") int br_id,Model model,HttpServletRequest request) {
		int result = replyService.ReplyDelete(br_id);
		if(result == 1) {
			model.addAttribute("msg", "댓글 삭제 완료");
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
		    return "alert";
		} 
		
		return "";
		
	}

}
