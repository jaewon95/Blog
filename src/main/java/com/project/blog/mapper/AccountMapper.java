package com.project.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.blog.entity.Account;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT * FROM account WHERE act_id = #{act_id} and act_pw = #{act_pw}")
	Account login(Account act); // 로그인
	
	@Select("SELECT count(*) FROM account WHERE act_id = #{act_id}")
	int userCreateTest(Account act);// 회원가입 중복 방지
	
	@Insert("insert into account (act_id, act_pw) values (#{act_id},#{act_pw})")
	int create(Account act); // 회원 생성

	

}
