package com.nuc.finish.service.impl;

import com.nuc.finish.dao.RegionMapper;
import com.nuc.finish.dao.UserMapper;
import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.Region;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.service.UserService;
import com.nuc.finish.util.IdentifyCodeUtil;
import com.nuc.finish.util.JsonUtil;
import com.nuc.finish.util.SmsUtil;
import com.nuc.finish.vo.LoginVO;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 11:59
 */
@Service
public class UserServiceImpl implements UserService {
    public static String CODE_ERROR = "验证码错误";
    public static String REGISTER_ERROR = "注册失败";
    public static String REGISTER_SUCCESS= "注册成功";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User login(LoginVO vo) throws CommonException {
        vo.setTel(vo.getUsername());
        User user = userMapper.selectByTelAndPassword(vo);
        if (user != null) {
            return user;
        }
        user =  userMapper.selectByUserNameAndPassword(vo);
        if (user != null) {
            fullRegion(user);
        }
        return user;
    }

    public String register(LoginVO vo) throws CommonException {
        String code = (String) redisTemplate.opsForValue().get(vo.getTel());
        if (!vo.getCode().equals(code)) {
            return CODE_ERROR;
        }
        int count = userMapper.insertUser(vo);
        return count > 0 ? REGISTER_SUCCESS : REGISTER_ERROR;
    }

    @Override
    public String getIdentifyCode(LoginVO vo) throws CommonException {
        String code = (String) redisTemplate.opsForValue().get(vo.getTel());
        if (code != null) {
            throw new CommonException(CommonResponseEnum.IDENTIFY_CODE_FAIL, "您操作的太快了");
        }
        String randomCode = IdentifyCodeUtil.getRandomCode();
        Integer smsCode = SmsUtil.sendMsg(vo.getTel(), randomCode);
        String message = SmsUtil.getMessage(smsCode);
        redisTemplate.opsForValue().set(vo.getTel(), randomCode, 1, TimeUnit.MINUTES);
        return message;
    }

    @Override
    public String changePwd(LoginVO vo) {
        String code = (String) redisTemplate.opsForValue().get(vo.getTel());
        if (!vo.getCode().equals(code)) {
            return "验证码不正确";
        }
        int count = userMapper.updatePwd(vo);
        if (count > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

    @Override
    public User updateUser(LoginVO vo, String token) {
        int count = userMapper.updateUser(vo);
        User user = userMapper.selectByUserId(vo.getId());
        fullRegion(user);
        redisTemplate.opsForValue().set(token, user);
        return user;
    }

    private void fullRegion(User user) {
        Region country;
        Region province;
        Region city;
        if (user.getCountry() != null) {
            country = regionMapper.selectById(user.getCountry());
            user.setCountryName(country.getRegionName());
        }
        if (user.getProvince() != null) {
             province = regionMapper.selectById(user.getProvince());
             user.setProvinceName(province.getRegionName());
        }
        if(user.getCity() != null) {
            city = regionMapper.selectById(user.getCity());
            user.setCityName(city.getRegionName());
        }
    }
}
