package com.project.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.project.blog.entity.Account;

@Mapper
public interface AdminMapper {
	
	// 블랙리스트 관리하기 - 블랙리스트 추가
	@Update("UPDATE account SET act_role = 0 WHERE act_num = #{act_num}")
	int blackListAdd(Account act);
	
	// 블랙리스트 관리하기 - 블랙리스트 해제
	@Update("UPDATE account SET act_role = 1 WHERE act_num = #{act_num}")
	int blackListRemove(Account act);
	

}
