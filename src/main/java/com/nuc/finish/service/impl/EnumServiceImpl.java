package com.nuc.finish.service.impl;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.dao.EnumMapper;
import com.nuc.finish.pojo.BaseEnum;
import com.nuc.finish.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/10 15:25
 */
@Service
public class EnumServiceImpl implements EnumService {

    @Autowired
    private EnumMapper enumMapper;

    @Override
    public List<LabelValue<String, String>> getVideoType() {
        List<BaseEnum> videoTypes = enumMapper.getVideoType();
        List<LabelValue<String, String>> res = new ArrayList<>();
        for (BaseEnum videoType : videoTypes) {
            LabelValue<String, String> labelValue = new LabelValue<>(videoType.getEnumCode(), videoType.getEnumDesc());
            res.add(labelValue);
        }
        return res;
    }
}
