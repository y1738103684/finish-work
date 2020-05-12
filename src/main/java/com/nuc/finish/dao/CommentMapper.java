package com.nuc.finish.dao;

import com.nuc.finish.pojo.Comment;
import com.nuc.finish.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 10:14
 */
@Mapper
public interface CommentMapper {
    int insertComment(@Param("vo") CommentVO vo);

    List<Comment> listCommentsByVideoId(@Param("videoId") Integer videoId);

    List<Comment> listCommentByParentId(@Param("parentId") Integer parentId);

    int updateCommentStatus(@Param("id") Integer id);
}
