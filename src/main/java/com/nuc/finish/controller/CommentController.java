package com.nuc.finish.controller;

import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.Comment;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.CommentService;
import com.nuc.finish.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 10:04
 */
@Controller
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment/add")
    @ResponseBody
    public Response addComment(@RequestBody CommentVO vo) throws CommonException {
        User user = getLoginUser();
        if (user == null) {
            throw new CommonException(CommonResponseEnum.USER_LOGIN_NOT);
        }
        vo.setUserId(user.getId());
        int count = commentService.addComment(vo);
        if (count <= 0) {
            throw new CommonException(CommonResponseEnum.COMMENT_INSERT_FAIL);
        }
        return Response.create(count);
    }

    @RequestMapping("/comment/query")
    @ResponseBody
    public Response queryComment(@RequestParam("videoId") Integer videoId) {
        List<Comment> commentList = commentService.getCommentByVideoId(videoId);
        return Response.create(commentList);
    }

    @RequestMapping("/comment/deleteComment")
    @ResponseBody
    public Response deleteComment(@RequestParam("commentId") Integer commentId) {
        int i = commentService.deleteComment(commentId);
        boolean b = i > 0 ? true : false;
        return Response.create(b);
    }
}
