package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = "医院管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalController {

    @Autowired
    private HospitalSetServiceImpl hospitalSetService;

    @ApiOperation(value = "查询表所有医院信息")
    @GetMapping("findAll")
    public List<HospitalSet> findAll(){
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    @ApiOperation(value = "逻辑删除一条表中的医院信息")
    @ApiParam(name = "id",value = "医院id",required = true)
    @DeleteMapping("{id}")
    public Boolean deleteOne(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }


}
