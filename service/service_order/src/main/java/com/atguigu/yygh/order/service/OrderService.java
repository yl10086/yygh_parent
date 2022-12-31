package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.atguigu.yygh.vo.order.OrderQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author YeLei
 * @Date 2022/12/23 13:37
 * @Version 1.0
 */
public interface OrderService extends IService<OrderInfo> {

    //生成订单
    Long saveOrder(String scheduleId, Long patientId);

    //根据订单id查询订单详情
    OrderInfo getOrderInfo(String orderId);

    //根据条件查询订单列表（分页）
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    //取消预约
    Boolean cancelOrder(Long orderId);
}
