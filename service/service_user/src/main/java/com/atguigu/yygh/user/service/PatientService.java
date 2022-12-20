package com.atguigu.yygh.user.service;

import com.atguigu.yygh.model.user.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/12/20 14:32
 * @Version 1.0
 */
public interface PatientService extends IService<Patient> {

    //获取就诊人列表
    List<Patient> findAllById(Long userId);

    //根据Patient表的主键id获取就诊人
    Patient getPatientId(Long id);

}
