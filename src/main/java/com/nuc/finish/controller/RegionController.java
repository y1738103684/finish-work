package com.nuc.finish.controller;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.pojo.Region;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.RegionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/20 15:41
 */
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping("/region/parentId")
    public Response getRegionByParentId(@RequestParam("parentId") Integer parentId) {
        List<LabelValue<Integer, String>> regionByParentId = regionService.getRegionByParentId(parentId);
        return Response.create(regionByParentId);
    }
}
