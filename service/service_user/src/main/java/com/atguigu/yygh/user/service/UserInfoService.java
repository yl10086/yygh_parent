package com.atguigu.yygh.user.service;

import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.vo.user.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/08 16:48
 * @Version 1.0
 */
public interface UserInfoService extends IService<UserInfo> {
    //用户手机号登陆接口
    Map<String, Object> loginUser(LoginVo loginVo);

    //查看数据库中是否存在openid
    UserInfo selectWxInfoOpenId(String openid);
}

