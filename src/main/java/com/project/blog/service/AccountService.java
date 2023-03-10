package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.entity.Account;
import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;
import com.project.blog.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper accountMapper;
	
	// 로그인
	public Account login(Account act) {
		return accountMapper.login(act);
	}
	
	// 계정 생성 아이디 중복검사
	public int userIdCheck(Account act) {
		
		return accountMapper.userIdCheck(act);
	}
		
	// 계정 생성 
	public int create(Account act) {	
		return accountMapper.create(act);
	}
	
	
	// 회원가입 되어있는 유저의 전체를 보여주기
	public List<Account> userAll(){
		return accountMapper.userAll();
	}
	
	// 회원 id로 선택조회
	public int selectOne(String act_id) {
		return accountMapper.selectOne(act_id);
	}
	
	// 가장 최근에 회원가입한 회원 id 조회
	public Account newUserSearch() {
		return accountMapper.newUserSearch();
	}
	
	// 현재 로그인한 유저의 pw 정보
	public String selectPassword(String act_id) {
		return accountMapper.selectPassword(act_id);
	};
	
	// 비밀번호 수정하기
	public int passwordUpdate(Account act) {
		return accountMapper.passwordUpdate(act);
	}
	 

}
