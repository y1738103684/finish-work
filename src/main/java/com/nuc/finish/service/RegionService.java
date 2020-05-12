package com.nuc.finish.service;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.dao.RegionMapper;
import com.nuc.finish.pojo.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/20 15:46
 */
public interface RegionService {
    List<LabelValue<Integer,String>> getRegionByParentId(Integer parentId);
}
