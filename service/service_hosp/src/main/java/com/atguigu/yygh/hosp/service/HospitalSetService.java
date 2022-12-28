package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.order.SignInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:26
 * @Version 1.0
 */
public interface HospitalSetService extends IService<HospitalSet> {

    //根据传递过来医院编号，查询数据库，查询签名
    String getSignKey(String hoscode);

    //获取医院签名信息
    SignInfoVo getSignInfoVo(String hoscode);
}
