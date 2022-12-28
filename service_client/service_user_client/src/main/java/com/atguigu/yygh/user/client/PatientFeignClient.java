package com.atguigu.yygh.user.client;

import com.atguigu.yygh.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author YeLei
 * @Date 2022/12/23 14:51
 * @Version 1.0
 */

@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {

    //获取就诊人信息，根据id获取
    @GetMapping("/api/user/patient/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id);
}
