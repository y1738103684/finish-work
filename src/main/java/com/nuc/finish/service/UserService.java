package com.nuc.finish.service;

import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.User;
import com.nuc.finish.vo.LoginVO;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.data.repository.query.Param;
import sun.rmi.runtime.Log;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 11:59
 */
public interface UserService {
    User login(LoginVO vo) throws CommonException;

    String register(LoginVO tel) throws CommonException;

    String getIdentifyCode(LoginVO vo) throws CommonException, InterruptedException, RemotingException, MQClientException, MQBrokerException;

    String changePwd(LoginVO vo);

    User updateUser(LoginVO vo, String token);

    int getVip(Integer userId);
}
