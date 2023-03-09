package com.project.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private int act_num;
	private String act_id;
	private String act_pw;
	private int act_role;
}
