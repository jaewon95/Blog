package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.entity.Account;
import com.project.blog.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper accountMapper;
	
	// 로그인
	public Account login(Account act) {
		return accountMapper.login(act);
	}
	
	// 계정 생성 중복검사
	public int userCreateTest(Account act) {
		int result = accountMapper.userCreateTest(act);
		System.out.println("result :" + result);
		return result;
	}
		
	// 계정 생성 
	public int create(Account act) {	
		return accountMapper.create(act);
		
		
	}

}
