package com.project.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.blog.entity.Reply;

import lombok.Delegate;

@Mapper
public interface ReplyMapper {
	

	// 해당 글에 대한 댓글
	@Select("SELECT bid, br_id, br_content, br_writer, date_format(br_date,'%Y-%m-%d') AS br_date FROM boardreply WHERE br_delete_yn = 0 and bid = ${bid}")
	List<Reply> ReplyListAll(int bid);
	
	// 댓글 작성하기
	@Insert("INSERT INTO boardreply (bid,br_content,br_writer) values (#{bid},#{br_content},#{br_writer})")
	int create(Reply rp);
	
	// 댓글 수정하기
	@Update("UPDATE boardreply SET br_content = #{br_content} WHERE br_id = #{br_id}")
	int ReplyUpdate(Reply reply);
	
	
	// 댓글 삭제하기
	@Update("UPDATE boardreply SET br_delete_yn = 1 WHERE br_id = #{br_id}")
	int ReplyDelete(int br_id);

}
