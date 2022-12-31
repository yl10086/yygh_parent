package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.atguigu.yygh.model.order.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/29 15:20
 * @Version 1.0
 */
public interface PaymentService extends IService<PaymentInfo> {

    //向支付记录表中添加信息
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    //更新订单状态
    void paySuccess(String out_trade_no, Map<String,String> resultMap);

    //获取支付记录
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);
}
