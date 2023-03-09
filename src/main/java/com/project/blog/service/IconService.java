package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.entity.Icon;
import com.project.blog.mapper.IconMapper;

@Service
public class IconService {
	
	@Autowired
	private IconMapper mapper;
	
	public int iconSetting(String act_id) {
		return mapper.iconSetting(act_id);
		
	}
	
	public Icon userIcon(String act_id) {
		return mapper.userIcon(act_id);
	}
	
	public int userIconUpdate(Icon icon) {
		return mapper.userIconUpdate(icon);
	}
	

}
