package com.nuc.finish.service;

import com.nuc.finish.pojo.Comment;
import com.nuc.finish.vo.CommentVO;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 10:04
 */
public interface CommentService {
    int addComment(CommentVO vo);

    List<Comment> getCommentByVideoId(Integer videoId);

    int deleteComment(Integer commentId);
}
