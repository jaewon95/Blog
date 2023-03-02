package com.project.blog.service;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.entity.Account;
import com.project.blog.mapper.AdminMapper;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	// 블랙리스트 관리하기 - 블랙리스트 추가
	public int blackListAdd(Account act) {
		return adminMapper.blackListAdd(act);
	}
		
	// 블랙리스트 관리하기 - 블랙리스트 해제
	public int blackListRemove(Account act) {
		return adminMapper.blackListRemove(act);
	}

	
}
