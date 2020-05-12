package com.nuc.finish.common;

import lombok.Data;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/19 15:03
 */
@Data
public class Pagination<T> {
    public static final Integer DEFAULT_PAGE_SIZE = 4;
    public static final Integer DEFAULT_PAGE_NUM = 1;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 数据行数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 结果集
     */
    private List<T> list;

    /**
     * 初始化分页参数
     */
    public void invoke() {
        if (this.pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        if (this.pageNum == null) {
            this.pageNum = DEFAULT_PAGE_NUM;
        }
    }
}
