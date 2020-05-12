package com.nuc.finish.service;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.pojo.BaseEnum;

import java.util.List;
import java.util.Map;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/10 15:24
 */

public interface EnumService {
    List<LabelValue<String, String>> getVideoType();
}
