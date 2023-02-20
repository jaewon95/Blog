package com.project.blog.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.entity.Reply;
import com.project.blog.mapper.ReplyMapper;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	// 댓글 전체조회
	public List<Reply> ReplyListAll(int bid) {
		return mapper.ReplyListAll(bid);
	}
	

	// 댓글 생성하기
	public int create(Reply rp) {
		return mapper.create(rp);
	}
	
	// 댓글 수정하기
	public int ReplyUpdate(int br_id) {
		return mapper.ReplyUpdate(br_id);
	}
		
	// 댓글 삭제하기
	public int ReplyDelete(int br_id) {
		return mapper.ReplyDelete(br_id);
	}


}
