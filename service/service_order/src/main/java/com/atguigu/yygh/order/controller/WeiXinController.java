package com.atguigu.yygh.order.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.enums.PaymentStatusEnum;
import com.atguigu.yygh.order.service.PaymentService;
import com.atguigu.yygh.order.service.WeiXinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/29 15:20
 * @Version 1.0
 */
@Api("微信支付")
@RestController
@RequestMapping("/api/order/weixin")
public class WeiXinController {

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private PaymentService paymentService;

    @ApiOperation("下单 生成二维码")
    @GetMapping("createNative/{orderId}")
    public Result createNative(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @PathVariable("orderId") Long orderId) {
        Map map = weiXinService.createNative(orderId);
        return Result.ok(map);
    }

    @ApiOperation("查询订单状态")
    @GetMapping("queryPayStatus/{orderId}")
    public Result queryPayStatus(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @PathVariable("orderId") Long orderId) {
        //调用微信接口，实现支付状态查询
        Map<String,String> resultMap = weiXinService.queryPayStatus(orderId);
        if (null == resultMap){
            return Result.fail().message("支付出错");
        }
        if ("SUCCESS".equals(resultMap.get("trade_state"))){
            //更新订单状态
            String out_trade_no = resultMap.get("out_trade_no"); //订单编码
            paymentService. paySuccess(out_trade_no, resultMap);
            return Result.ok().message("支付成功！");
        }
        return Result.ok().message("支付中");
    }
}
