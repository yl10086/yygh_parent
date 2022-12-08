package com.atguigu.yygh.hosp.controller.api;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/12/06 15:14
 * @Version 1.0
 */
@Api("医院管理接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable Integer page,
                                @PathVariable Integer limit,
                                HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        int totalPages = pageModel.getTotalPages();
        return Result.ok(pageModel);
    }

    @ApiOperation("根据医院名称模糊查询")
    @GetMapping("findByHosName/{hosname}")
    public Result findByHosName(@PathVariable String hosname){
        List<Hospital> list = hospitalService.findByHosname(hosname);
        return Result.ok(list);
    }

    @ApiOperation("根据医院编号获取科室信息")
    @GetMapping("department/{hoscode}")
    public Result index(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDepartmentTree(hoscode);
        return Result.ok(list);
    }

    @ApiOperation("根据医院编号获取医院预约挂号详情列表")
    @GetMapping("findHospDetail/{hoscode}")
    public Result hospItem(@PathVariable String hoscode){
        Map<String,Object> map = hospitalService.item(hoscode);
        return Result.ok(map);
    }

}
