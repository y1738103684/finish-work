package com.nuc.finish.controller;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/10 15:23
 */
@RestController
public class EnumController {

    @Autowired
    private EnumService enumService;

    @RequestMapping("/enum/video_type")
    public Response getVideoType() {
        List<LabelValue<String, String>> videoType = enumService.getVideoType();

        return Response.create(videoType);
    }
}
