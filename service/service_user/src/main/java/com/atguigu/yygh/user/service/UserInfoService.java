package com.atguigu.yygh.user.service;

import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.vo.user.LoginVo;
import com.atguigu.yygh.vo.user.UserAuthVo;
import com.atguigu.yygh.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    //用户认证接口
    void authUser(Long userId, UserAuthVo userAuthVo);

    //用户列表分页条件查询
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    //用户锁定
    void lockUser(Long userId, Integer status);

    //用户详情
    Map<String, Object> show(Long userId);

    //认证审批（2通过  -1不通过）
    void approval(Long userId, Integer authStatus);
}

