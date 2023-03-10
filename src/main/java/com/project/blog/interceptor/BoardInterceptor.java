package com.project.blog.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.blog.entity.Account;
import com.project.blog.entity.Board;

public class BoardInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	void freeset(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter printwriter = response.getWriter();
		printwriter.print("<script>alert('올바른 접근이 아닙니다'); location.href='/'; </script>"); // alert 창 띄우고 "/" 로 이동
		printwriter.flush();
		printwriter.close();
	}

	// url 요청 들어가기 전 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionId");
		String url = request.getRequestURI(); // 요청 주소값
		String admin = "admin";

		// 글쓰기 체크 => 로그인 상태가 아니라면 진행못하게 막기
		if (url.equals("/user/create")) { // url에 /user/create 요청이 들어오면
			System.out.println("글쓰기 접근");
			if (session.getAttribute("loginSession") == null) {

				response.setContentType("text/html; charset=utf-8"); // utf8 설정해주기

				PrintWriter printwriter = response.getWriter();

				printwriter.print("<script>alert('로그인이 필요합니다'); location.href='/'; </script>"); // alert 창 띄우고 "/" 로 이동
				printwriter.flush();
				printwriter.close();

				return false; // 인터셉터 통과X
			}
		} // ==============================================================================
		
		
		// 관리자 접근 관리하기
		if(url.equals("/admin/userManage")) {
			if (session.getAttribute("loginSession") == null) {
				freeset(response);
			} else {
				if(id.equals(admin)) {
					System.out.println("관리자");
				} else {
					freeset(response);
				}
			}
			
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
	
	
	
	// url 요청 들어간 후 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionId");
		String url = request.getRequestURI(); // 요청 주소값
		
		// 마이페이지 접근 차단하기
		if(url.contains("/user/myPage")) {
			if(session.getAttribute("loginSession") == null) { // session 값이 비어있다면
				freeset(response);
			} 
		}
		
		if(url.contains("/user/myPage/Password")) {
			if(session.getAttribute("loginSession") == null) { // session 값이 비어있다면
				freeset(response);
			} 
		}
			
			
			
			
			

		// 게시글 update 강제 접근 차단
		if (url.contains("/user/update/")) {
			Board board = (Board) modelAndView.getModelMap().getAttribute("updateData");
			String writer = board.getBwriter(); // 글의 작성자 구하기
			
			if(session.getAttribute("loginSession") == null) { // session 값이 비어있다면
				freeset(response);
			} else { // sessionId 값이 들어있음
				
				if(id.equals(writer)) {// 비교해주기 
					// 같을경우
				} else {
					// 다를경우
					freeset(response);
				}
			}
		}
		
		// 삭제처리 된 게시글 접근 막기
		if(url.contains("/user/detail/")) {
			Board boardSelectOne = (Board) modelAndView.getModelMap().getAttribute("selectOne");
			System.out.println("인터셉터 detail"+boardSelectOne.toString()); 
			if(boardSelectOne.isBdelete_yn() == true) {
				freeset(response);
			}
		}
	
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
