package com.nuc.finish.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/19 15:08
 */
public class PaginationUtil {

    public static void PageStart(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }
}
