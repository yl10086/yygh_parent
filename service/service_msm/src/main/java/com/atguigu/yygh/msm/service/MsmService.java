package com.atguigu.yygh.msm.service;

import com.atguigu.yygh.vo.msm.MsmVo;

/**
 * @Author YeLei
 * @Date 2022/12/12 14:32
 * @Version 1.0
 */
public interface MsmService {
    //发送手机验证码
    boolean send(String phone, String code);

    //qq邮箱验证方式
    boolean sendEmail(String email, String code);

    //通过MQ发送短信
    boolean send(MsmVo msmVo);
}
