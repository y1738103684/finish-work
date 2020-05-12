package com.nuc.finish.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/9 16:37
 */
public class SmsUtil {
    private static final String SMS_URL = "http://utf8.api.smschinese.cn/";
    private static final String SMS_USER = "y1738103684";
    private static final String SMS_KEY = "d41d8cd98f00b204e980";
    private static final Integer INIT_VALUE = -200;

    /**
     * 发送短信
     * @param phoneNum 发送人电话号
     * @return
     */
    public static Integer sendMsg(String phoneNum, String code) {
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(SMS_URL);
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair[] data = {
                new NameValuePair("Uid", SMS_USER),
                new NameValuePair("Key", SMS_KEY),
                new NameValuePair("smsMob", phoneNum),
                new NameValuePair("smsText", "验证码: " + code)
        };
        postMethod.setRequestBody(data);
        try {
            httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer res = INIT_VALUE;
        try {
            res = Integer.parseInt(new String(postMethod.getResponseBodyAsString().getBytes("gbk")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        postMethod.releaseConnection();
        return res;
    }


    public static void send(String mobile) {
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(SMS_URL);
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair[] data = {
                new NameValuePair("Uid", SMS_USER),
                new NameValuePair("Key", SMS_KEY),
                new NameValuePair("smsMob", mobile),
                new NameValuePair("smsText", "验证码: ")
        };
        postMethod.setRequestBody(data);
        try {
            int i = httpClient.executeMethod(postMethod);
            System.out.println("短信数量" + i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(Integer code){
        String message;
        if(code > 0 ) {
            message = "短信发送成功";
        }else if(code == -1){
            message = "SMS-没有该用户账户";
        }else if(code == -2){
            message = "SMS-接口密钥不正确";
        }else if(code == -21){
            message = "SMS-MD5接口密钥加密不正确";
        }else if(code == -3){
            message = "SMS-短信数量不足";
        }else if(code == -11){
            message = "SMS-该用户被禁用";
        }else if(code == -14){
            message = "SMS-短信内容出现非法字符";
        }else if(code == -4){
            message = "SMS-手机号格式不正确";
        }else if(code == -41){
            message = "SMS-手机号码为空";
        }else if(code == -42){
            message = "SMS-短信内容为空";
        }else if(code == -51){
            message = "SMS-短信签名格式不正确接口签名格式为：【签名内容】";
        }else if(code == -6){
            message = "SMS-IP限制";
        }else{
            message = "其他错误";
        }
        return message;
    }
}
