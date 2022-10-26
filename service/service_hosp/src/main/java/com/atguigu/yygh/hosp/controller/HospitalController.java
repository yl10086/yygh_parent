package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:36
 * @Version 1.0
 * 方便复制粘贴访问
 * http:localhost:8201/admin/hosp/hospitalSet/findAll
 */

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalController {

    @Autowired
    private HospitalSetServiceImpl hospitalSetService;

    //1、查询医院设置表所有信息
    @GetMapping("findAll")
    public List<HospitalSet> findAll(){
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    //2、删除一条表信息
    @DeleteMapping("{id}")
    public Boolean deleteOne(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }
}
