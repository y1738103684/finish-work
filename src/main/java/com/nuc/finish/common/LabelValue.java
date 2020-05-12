package com.nuc.finish.common;

import lombok.Data;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/11 10:49
 */
@Data
public class LabelValue<K, T> {
    private K label;
    private T value;

    public LabelValue(K label, T value) {
        this.label = label;
        this.value = value;
    }
}
