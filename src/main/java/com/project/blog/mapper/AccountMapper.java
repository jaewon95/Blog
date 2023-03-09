package com.project.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.blog.entity.Account;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT * FROM account WHERE act_id = #{act_id} and act_pw = #{act_pw} and act_role != 0")
	Account login(Account act); // 로그인
	
	@Insert("INSERT INTO account (act_id, act_pw) VALUES (#{act_id},#{act_pw})")
	int create(Account act); // 회원 생성
	
	@Select("SELECT COUNT(*) FROM account WHERE act_id = #{act_id}")
	int userIdCheck(Account act); // 회원가입 아이디 중복검사
	
	@Select("SELECT * FROM account order by act_num desc limit 1")
	Account newUserSearch(); // 가장 최근에 회원가입한 회원 id 조회
	
	@Select("SELECT * FROM account WHERE act_role != 2")
	List<Account> userAll(); // 회원 전체 목록 보기
	
	@Select("SELECT * FROM account WHERE act_id = #{act_id}")
	int selectOne(String act_id); //회원 선택 조회
	
	
	
	
	

	

}
