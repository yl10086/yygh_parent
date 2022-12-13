package com.atguigu.yygh.msm.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.msm.service.MsmService;
import com.atguigu.yygh.msm.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author YeLei
 * @Date 2022/12/12 14:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("发送手机验证码")
    @GetMapping("sendCode/{phone}")
    public Result sendCode(@PathVariable String phone){
        //从redis获取验证码，如果获取到了返回ok
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //如果从redis获取不到
        //生成一个验证码
        code = RandomUtil.getSixBitRandom();
        //通过整合短信服务进行发送
        boolean isSend = msmService.send(phone,code);
        //生成验证码放到redis中，设置有效时间
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.fail("验证码发送失败！");
        }
    }

    @ApiOperation("qq邮箱验证方式")
    @GetMapping("/sendEmail/{email}")
    public Result sendEmail(@PathVariable String email){
        //从redis获取验证码，如果获取到了返回ok
        String code = redisTemplate.opsForValue().get(email);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //如果从redis获取不到
        //生成一个验证码
        code = RandomUtil.getSixBitRandom();
        boolean isSuccess = msmService.sendEmail(email,code);
        if (isSuccess) {
            //生成验证码放到redis中，设置有效时间
            redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.fail("验证码发送失败！");
        }
    }
}
