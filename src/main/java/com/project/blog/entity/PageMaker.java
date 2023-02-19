package com.project.blog.entity;

// 페이징 처리(1 2 3 4 5 ...)를 만드는 클래스
public class PageMaker {
	private Criteria cri;
	private int totalCount; // 총게시글의 수
	private int startPage; // 시작페이지번호
	// [1] 2 3 4 5 ...
	// 1 2 3 [4] 5 .... 
	// 11 12 13 14 [15] ...
	private int endPage;// 끝 페이지 번호
	// 마지막 페이지는 항상 유동적임
	// 11 [12] ,  11 12 13 [14]
	// 이렇게 끝나기도 하니까
	private boolean prev; // true면 이전버튼 생성 false 는 ㄴㄴ
	private boolean next; // true면 다음버튼 생성 false 는 ㄴㄴ
	private int displayPageNum=5; // 몇개씩 보여줄거냐 이거임
	
	// 총 게시글의 수를 구하는 메서드
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		makePagin(); // 메서드 생성
	}
	
	private void makePagin() { 
		// 1. 화면에 보여질 마지막 페이지 번호 구하기
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
									//  (현재페이지번호 / 보여줄페이지전체) * 보여줄페이지전체 
									//  Math.ceil( 3 / 10.0 ) * 10
									//        = 1 * 10 
		
		// 2. 화면에 보여질 시작 페이지 번호 구하기
		startPage = (endPage - displayPageNum) + 1;
			// 1~10 [ 1 ] = (10 - 10 ) + 1 
			// 11~20 [ 11 ] = (20 - 10 ) + 1
		if(startPage <= 0) startPage = 1; // 페이지는 무조건 1부터 시작임
		
		// 3.전체 마지막 페이지 계산하기 ( 항상 유동적인 부분 ) : 말 그대로 전체 번호 수 ex) 24개다 뭐 이런거
		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
		System.out.println(tempEndPage);
		
		
		// 4.화면에 보여질 마지막 페이지 유효성 체크
		if(tempEndPage < endPage) {
			endPage = tempEndPage;
		}
		
		// 5.이전페이지 버튼(링크) 존재 여부 true , false로 처리하기
		prev = (startPage == 1) ? false : true;
		
		// 6.다음페이지 버튼(링크) 존재 여부
		// 10(내가 설정한 페이지의 번호) < 14(페이지 전체의 번호)  
		next = (endPage < tempEndPage) ? true : false;
	}			

	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
	
	
	
	
	
	
}
