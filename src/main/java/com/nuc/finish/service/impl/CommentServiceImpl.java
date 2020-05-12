package com.nuc.finish.service.impl;

import com.nuc.finish.dao.CommentMapper;
import com.nuc.finish.pojo.Comment;
import com.nuc.finish.service.CommentService;
import com.nuc.finish.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 10:05
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(CommentVO vo) {
        vo.setUpdateTime(new Date());
        vo.setCreateTime(new Date());
        return commentMapper.insertComment(vo);
    }

    @Override
    public List<Comment> getCommentByVideoId(Integer videoId) {
        return commentMapper.listCommentsByVideoId(videoId);
    }

    @Override
    public int deleteComment(Integer commentId) {
        return commentMapper.updateCommentStatus(commentId);
    }
}
