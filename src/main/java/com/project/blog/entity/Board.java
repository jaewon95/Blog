package com.project.blog.entity;

import java.sql.Date;

import lombok.Data;


@Data
public class Board {
	private int bid;
	private String btitle;
	private String bwriter;
	private String bcontent;
	private Date bdate;
	private boolean bdelete_yn;
	
	// 검색필터
	private String keyword;
	
}
