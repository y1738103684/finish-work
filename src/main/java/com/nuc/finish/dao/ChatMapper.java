package com.nuc.finish.dao;

import com.nuc.finish.pojo.Chat;
import com.nuc.finish.vo.ChatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:01
 */
@Mapper
public interface ChatMapper {

    Chat selectByToAndFrom(@Param("query") ChatVO vo);

    int insertChat(@Param("query") ChatVO vo);

    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    List<Chat> listByCreateTime(@Param("query") ChatVO vo, @Param("date")Date date);
}
