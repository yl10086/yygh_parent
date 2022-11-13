package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:26
 * @Version 1.0
 */
public interface HospitalSetService extends IService<HospitalSet> {

    String getSignKey(String hoscode);
}
