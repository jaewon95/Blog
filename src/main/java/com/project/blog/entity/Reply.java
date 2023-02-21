package com.project.blog.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Reply {

	private int bid; // 글번호
	private int br_id;
	private String br_writer;
	private String br_content;
	private Date br_date;
	private boolean br_delete_yn;
	
}
