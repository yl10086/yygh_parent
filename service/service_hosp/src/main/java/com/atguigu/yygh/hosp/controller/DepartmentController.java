package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/12/02 13:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/hosp/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("根据医院编号，查询医院所有科室列表")
    @GetMapping("/getDepartmentList/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDepartmentTree(hoscode);
        return Result.ok(list);
    }
}
