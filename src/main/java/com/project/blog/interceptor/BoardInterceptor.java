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
		}
		
		// 게시글 삭제 접근 막기
		if (url.contains("/user/delete/")) {
			
			
			if(session.getAttribute("loginSession") == null) { // session 값이 비어있다면 접근 불가능
				freeset(response);
			} else {
				System.out.println(session.getAttribute("loginSession").toString());
			}
			
			// 세션값과 해당 게시글의 작성자가 다르다면 불가능하게 해야함
			/*
			 *  DB 부분을 수정해줘야함
			 *  현재 Board 테이블과 Account 테이블은 연관관계가 아예 없는 상태 
			 *  bwriter 과 act_id 를 연관관계 맺어줘야함
			 *  이렇게 변경해주면 만약에 act_id 의 값과 session의 값이 다르다면 삭제가 불가능하도록
			 *  변경해주는게 가능함
			 *  
			 *  if(session.getAttribute("sessionId") == ) ...?
			 *  해당 글에 접근하기 전에 막는다..?
			 *  
			 *  해당글의 act_id 와 세션,,,?
			 *  해당글의 act_id 를 어떻게 구하지 어아어아어아아아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
			 *  
			 *  현재 get매핑인거 ---> post매핑으로 변경하기
			 * */
			
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
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}