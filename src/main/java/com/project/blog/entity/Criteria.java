package com.project.blog.entity;

public class Criteria {
	private int page; // 현재 페이지 번호
	private int perPageNum; // 한 페이지에 보여줄 게시글의 수
				
	public Criteria() { 
		this.page=1;
		this.perPageNum=6;
	}
	
	// 메서드 생성
	public int getPageStart() {
		return (page-1) * perPageNum;
	}
	
	// 스프링 Bean의 특징 = DI
	// set, get을 다 만들지 않아도 사용이 가능함 BoardMapper에서 #{pageStart}를 
	// 사용가능한 이유임
	// 강의 : https://www.youtube.com/watch?v=bYu9MNLBvX0
	
	// 현재 페이지의 글 시작 번호 구하기
	//public int getPageStart() { // 1페이지, 2페이지, 3페이지
	//	return (page-1) * perPageNum; // 0*10=0~, 1*10=10~, 2*10=20~ : 
								// Limit 를 이용해서 구할것 limit 0,10
				// 우리가 의미하는 1~10까지의 번호는 sql에서는
				// 0~9까지라서 -1씩 해줘야함 sql에서는 0부터 숫자를 구하기 시작
	//}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
	
	
	
}
