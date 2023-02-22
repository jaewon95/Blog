package com.project.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.entity.Board;
import com.project.blog.service.BoardService;

@RestController
public class SearchController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/searchPro")
	public List<Board> listAll(@RequestParam String keyword, Board board) {
		
		board.setKeyword(keyword);
		
		System.out.println("키워드 목록 " + boardService.SearchListAll(board).toString()); 
		
		
		return boardService.SearchListAll(board);
		
	}
	
	
	// 검색 후 조회
	

}
