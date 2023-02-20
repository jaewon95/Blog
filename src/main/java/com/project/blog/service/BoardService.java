package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int create(Board board) {
		return mapper.create(board);
	}
	
	// 글수정
	public int update(Board board) {
		return mapper.update(board);
	}
	
	// 글삭제
	public int delete(int bid) {
		return mapper.delete(bid);
	}
	
	public int totalCount() {
		return mapper.totalCount();
	};

}
