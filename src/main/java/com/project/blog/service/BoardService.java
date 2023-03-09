package com.project.blog.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	// 글전체조회 - 페이징
	public List<Board> listAll(Criteria cri){
		return mapper.listAll(cri);
	}
	
	// 글선택조회
	public Board selectOne(int bid) {
		return mapper.selectOne(bid);
	}
	
	// 글생성
	public int create(Board board , MultipartFile file) throws Exception {
		
		return mapper.create(board);
	}
	
	// 글수정
	public int update(Board board) throws IllegalStateException, IOException {
		System.out.println("Service 체크 : "+board.toString());
		return mapper.update(board);
	}
	
	// 글삭제
	public int delete(Board board) {
		return mapper.delete(board);
	}
	
	public int totalCount() {
		return mapper.totalCount();
	};
	
	// 검색 전체조회
	public List<Board> SearchListAll(Board board) {
		return mapper.SearchListAll(board);
	};
	
}
