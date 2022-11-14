package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/14 18:54
 * @Version 1.0
 */
public interface DepartmentService {
    //上传科室信息
    void save(Map<String, Object> paramMap);

    //分页查询科室接口
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    //根据医院编号和科室编号删除对应科室
    void deleteDepartment(String hoscode, String depcode);
}
