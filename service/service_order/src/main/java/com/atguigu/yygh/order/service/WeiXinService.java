package com.atguigu.yygh.order.service;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/29 15:30
 * @Version 1.0
 */
public interface WeiXinService {

    //下单 生成二维码
    Map createNative(Long orderId);

    //调用微信接口，实现支付状态查询
    Map<String, String> queryPayStatus(Long orderId);

    //退款
    Boolean refund(Long orderId);
}
