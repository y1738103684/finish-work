package com.nuc.finish.dao;

import com.nuc.finish.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 15:36
 */
@Mapper
public interface OrderMapper {
    Order selectByVideoIdAndUserId(@Param("videoId") Integer videoId, @Param("userId") Integer userId);

    int insertOrder(@Param("order") Order order);

    List<Order> listByUserId(@Param("userId") Integer userId);
}
