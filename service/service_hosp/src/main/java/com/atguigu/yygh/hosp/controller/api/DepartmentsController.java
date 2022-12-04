package com.atguigu.yygh.hosp.controller.api;

import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.helper.HttpRequestHelper;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.common.util.MD5;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/14 19:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/hosp")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "删除科室接口")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号 和 科室编号
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        //1、获取医院系统传递过来的签名，进行MD5加密
        String hospSign = (String) paramMap.get("sign");
        //2、根据传递过来医院编号，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3、把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4、判断签名是否一致
        if (!hospSign.equals(signKeyMd5)){
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        departmentService.deleteDepartment(hoscode,depcode);
        return Result.ok();
    }

    @ApiOperation(value = "查询科室接口")
    @PostMapping("department/list")
    public Result listDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //1、获取医院系统传递过来的签名，进行MD5加密
        String hospSign = (String) paramMap.get("sign");
        //2、根据传递过来医院编号，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3、把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4、判断签名是否一致
        if (!hospSign.equals(signKeyMd5)){
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        //分页
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1: Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1: Integer.parseInt((String) paramMap.get("limit"));
        //调用service方法
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> departmentPage = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(departmentPage);
    }

    @ApiOperation(value = "上传科室信息")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //1、获取医院系统传递过来的签名，进行MD5加密
        String hospSign = (String) paramMap.get("sign");
        //2、根据传递过来医院编号，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3、把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4、判断签名是否一致
        if (!hospSign.equals(signKeyMd5)){
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        //调用service方法
        departmentService.save(paramMap);
        return Result.ok();
    }
}
