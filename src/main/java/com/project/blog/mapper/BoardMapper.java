package com.project.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;


@Mapper
public interface BoardMapper {
	
	// 글전체조회 - 페이징
	@Select(  " SELECT * FROM board WHERE bdelete_yn=0 "
			+ " ORDER BY bid desc "
			+ " limit #{pageStart},#{perPageNum} " )
	List<Board> listAll(Criteria cri); 

	// 글선택조회
	@Select("SELECT * FROM board WHERE bid = #{bid}")
	Board selectOne(int bid);
	
	// 글생성
	@Insert("INSERT INTO board (btitle, bwriter, bcontent, filename, filepath) values (#{btitle},#{bwriter}, #{bcontent}, #{filename} ,#{filepath})")
	int create(Board board);
	
	// 글수정
	@Update("UPDATE board SET btitle = #{btitle}, bcontent = #{bcontent}, filename = #{filename}, filepath = #{filepath}  WHERE bid = #{bid}")
	int update(Board board);
	
	// 글삭제
	@Update("UPDATE board SET bdelete_yn = 1 WHERE bid = #{bid}")
	int delete(Board board);
	
	@Select("SELECT count(*) from board WHERE bdelete_yn = 0")
	int totalCount();
	
	// 검색 전체조회
	@Select(  "SELECT * FROM board "
			+ "WHERE bdelete_yn = 0 AND "
			+ "(bwriter like CONCAT('%',#{keyword},'%') OR "
			+ "btitle like CONCAT('%',#{keyword},'%') OR "
			+ "bcontent like CONCAT('%',#{keyword},'%'))"
			)
	List<Board> SearchListAll(Board board);
	
}
