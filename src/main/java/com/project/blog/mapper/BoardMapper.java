package com.project.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.blog.entity.Board;
import com.project.blog.entity.Criteria;


@Mapper
public interface BoardMapper {
	
	// 글전체조회 - 페이징
	@Select("SELECT * FROM board ORDER BY bid desc"
			+ " limit #{pageStart},#{perPageNum}")
	List<Board> listAll(Criteria cri); 


	// 글선택조회
	@Select("SELECT * FROM board WHERE bid = #{bid}")
	Board selectOne(int bid);
	
	// 글생성
	@Insert("INSERT INTO board (btitle, bwriter, bcontent) values (#{btitle},#{bwriter}, #{bcontent})")
	int create(Board board);
	
	// 글수정
	@Update("UPDATE board SET btitle = #{btitle}, bcontent = #{bcontent}  WHERE bid = #{bid}")
	int update(Board board);
	
	// 글삭제
	@Delete("DELETE FROM board WHERE bid = #{bid}")
	int delete(int bid);
	
	@Select("SELECT count(*) from board")
	int totalCount();
	
	
}
