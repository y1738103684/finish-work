package com.nuc.finish.service.impl;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.dao.RegionMapper;
import com.nuc.finish.pojo.Region;
import com.nuc.finish.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/20 15:47
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<LabelValue<Integer, String>> getRegionByParentId(Integer parentId) {
        List<Region> regions = regionMapper.listByParentId(parentId);
        if (CollectionUtils.isEmpty(regions)) {
            return null;
        } else {
            List<LabelValue<Integer, String>> res = new ArrayList<>();
            for (Region region : regions) {
                res.add(new LabelValue<>(region.getId(), region.getRegionName()));
            }
            return res;
        }
    }

}
