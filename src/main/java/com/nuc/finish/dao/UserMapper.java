package com.nuc.finish.dao;

import com.nuc.finish.pojo.User;
import com.nuc.finish.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sun.rmi.runtime.Log;

import java.lang.management.LockInfo;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 12:19
 */
@Mapper
public interface UserMapper {
    User selectByUserNameAndPassword(@Param("vo") LoginVO vo);

    User selectByTelAndPassword(@Param("vo") LoginVO vo);

    User selectByUserId(@Param("id") Integer id);

    int insertUser(@Param("vo") LoginVO vo);

    int updateVip(@Param("id") Integer id);

    int updatePwd(@Param("vo")LoginVO vo);

    int updateUser(@Param("vo") LoginVO vo);

    int updatePoints(@Param("points") Integer points, @Param("id") Integer userId);

    User selectByTel(@Param("tel") Long tel);
}
