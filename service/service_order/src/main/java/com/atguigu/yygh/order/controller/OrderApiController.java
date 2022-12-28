package com.atguigu.yygh.order.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author YeLei
 * @Date 2022/12/23 13:43
 * @Version 1.0
 */
@Api("订单接口")
@RestController
@RequestMapping("/api/order/orderInfo")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("生成订单")
    @PostMapping("auth/submitOrder/{scheduleId}/{patientId}")
    public Result submitOrder(
            @ApiParam(name = "scheduleId", value = "排班id", required = true)
            @PathVariable String scheduleId,
            @ApiParam(name = "patientId", value = "就诊人id", required = true)
            @PathVariable Long patientId) {
        return Result.ok(orderService.saveOrder(scheduleId, patientId));
    }

    @ApiOperation("根据订单id查询订单详情")
    @GetMapping("auth/getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId) {
        return Result.ok(orderService.getOrderInfo(orderId));
    }

}
