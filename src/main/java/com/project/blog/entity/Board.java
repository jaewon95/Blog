package com.project.blog.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int bid;
	private String btitle;
	private String bwriter;
	private String bcontent;
	private Date bdate;
	private boolean bdelete_yn;
	
	// 검색필터
	private String keyword;
	
	// 파일
	private String filename; 
	private String filepath;
	
}
