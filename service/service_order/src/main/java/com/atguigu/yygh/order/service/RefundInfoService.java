package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.PaymentInfo;
import com.atguigu.yygh.model.order.RefundInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author YeLei
 * @Date 2022/12/30 16:04
 * @Version 1.0
 */

public interface RefundInfoService extends IService<RefundInfo> {

    //保存退款记录
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);
}
