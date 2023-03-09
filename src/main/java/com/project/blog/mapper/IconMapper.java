package com.project.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.blog.entity.Icon;


@Mapper
public interface IconMapper {
	
	@Insert("INSERT INTO icon (act_id, iconSrc) VALUE (#{act_id}, 'https://cdn-icons-png.flaticon.com/512/9207/9207952.png')") 
	int iconSetting(String act_id); // 회원가입시 기본 아이콘 적용해주기
	
	@Select("SELECT iconSrc FROM icon WHERE act_id = #{act_id}")
	Icon userIcon(String act_id);
	// 해당 유저의 아이콘 가져오기
	
	@Update("UPDATE icon SET iconSrc = #{iconSrc} WHERE act_id = #{act_id}")
	int userIconUpdate(Icon icon);
	// 아이콘 변경
	
	
	
	

}
