package com.atguigu.yygh.user.api;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.utils.AuthContextHolder;
import com.atguigu.yygh.model.user.Patient;
import com.atguigu.yygh.user.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/12/20 14:31
 * @Version 1.0
 */
@Api("就诊人管理接口")
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {

    @Autowired
    private PatientService patientService;

    @ApiOperation("获取就诊人列表")
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request){
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllById(userId);
        return Result.ok(list);
    }

    @ApiOperation("添加就诊人")
    @PostMapping("auth/save")
    public Result save(@RequestBody Patient patient, HttpServletRequest request){
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    @ApiOperation("根据Patient表的主键id获取就诊人")
    @GetMapping("auth/getPatient/{id}")
    public Result getPatientById(@PathVariable Long id){
        Patient patient = patientService.getPatientId(id);
        return Result.ok(patient);
    }

    @ApiOperation("修改就诊人")
    @PostMapping("auth/updatePatient")
    public Result updatePatient(@RequestBody Patient patient){
        patientService.updateById(patient);
        return Result.ok();
    }

    @ApiOperation("删除就诊人")
    @DeleteMapping("auth/deletePatient/{id}")
    public Result deletePatient(@PathVariable Long id){
        patientService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("根据就诊人id获取就诊人信息")
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(
            @ApiParam(name = "id", value = "就诊人id", required = true)
            @PathVariable("id") Long id) {
        return patientService.getPatientId(id);
    }

}
