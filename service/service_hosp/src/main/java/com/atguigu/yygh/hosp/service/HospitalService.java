package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/13 13:13
 * @Version 1.0
 */
public interface HospitalService {

    //上传医院信息接口
    void save(Map<String, Object> paramMap);

    //根据医院编号查询
    Hospital getByHoscode(String hoscode);
}
