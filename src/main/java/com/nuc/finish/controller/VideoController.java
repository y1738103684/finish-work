package com.nuc.finish.controller;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.exception.CommonException;
import com.nuc.finish.model.VideoModel;
import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.VideoService;
import com.nuc.finish.util.FileUploadUtil;
import com.nuc.finish.vo.ShowVideoVO;
import com.nuc.finish.vo.VideoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 22:33
 */
@RestController
public class VideoController extends BaseController{

    @Autowired
    private VideoService videoService;

    @RequestMapping("/video/show")
    public Response getVideos(@RequestBody ShowVideoVO vo) {
        if (vo.getPagination() == null) {
            Pagination page = vo.getPagination();
            page = new Pagination();
            page.invoke();
            vo.setPagination(page);
        } else {
            Pagination pagination = vo.getPagination();
            pagination.invoke();
        }
        PageInfo<VideoModel> info = videoService.showVideo(vo.getPagination(), vo.getType());
        return Response.create(info);
    }

    @RequestMapping("/video/upload")
    public Response uploadVideo(@RequestParam("file")MultipartFile file) throws CommonException {
        String address = FileUploadUtil.uploadFile(file);
        if (address == null) {
            throw new CommonException(CommonResponseEnum.FILE_UPLOAD_FAIL);
        }
        return Response.create(address);
    }

    @RequestMapping("/video/photo-upload")
    public Response photoUploadVideo(@RequestParam("file")MultipartFile file) throws CommonException {
        String photo = FileUploadUtil.uploadFile(file);
        if (photo == null) {
            throw new CommonException(CommonResponseEnum.FILE_UPLOAD_FAIL);
        }
        return Response.create(photo);
    }

    @RequestMapping("/video/getVideo")
    public Response getVideo(@RequestParam("videoId") Integer id) throws CommonException {
        Video videoById = videoService.getVideoById(id);
        if (videoById == null) {
            return Response.create("视频信息已过期");
        } else {
            return Response.create(videoById);
        }
    }

    @RequestMapping("/video/recommend")
    public Response recommend() {
        List<Video> recommend = videoService.recommend();
        return Response.create(recommend);
    }

    @RequestMapping("/video/addVideo")
    public Response addVideo(@RequestBody VideoVO vo) {
        int i = videoService.uploadVideo(vo);
        return Response.create(i);
    }


}
