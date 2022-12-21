package com.atguigu.yygh.user.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/21 14:07
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("用户列表分页条件查询")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       UserInfoQueryVo userInfoQueryVo){
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = userInfoService.selectPage(pageParam,userInfoQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("用户锁定")
    @GetMapping("lock/{userId}/{status}")
    public Result lock(@PathVariable Long userId,
                       @PathVariable Integer status){
        userInfoService.lockUser(userId,status);
        return Result.ok();
    }

    @ApiOperation("用户详情")
    @GetMapping("showUserInfo/{userId}")
    public Result showUserInfo(@PathVariable Long userId){
        Map<String,Object> map = userInfoService.show(userId);
        return Result.ok(map);
    }

    @ApiOperation("认证审批（2通过  -1不通过）")
    @GetMapping("approval/{userId}/{authStatus}")
    public Result approval(@PathVariable Long userId,
                               @PathVariable Integer authStatus){
        userInfoService.approval(userId,authStatus);
        return Result.ok();
    }
}
