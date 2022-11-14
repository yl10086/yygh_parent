package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.DepartmentRepository;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/14 18:55
 * @Version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //上传科室接口
    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数 map 集合转换成 Department 对象
        String mapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(mapString, Department.class);
        //根据医院编号 和 科室编号 进行查询
        Department departmentExist = departmentRepository
                .getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());

        //存在,进行修改操作
        if (departmentExist != null){
            department.setId(department.getId());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }else { //不存在,进行添加操作
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    //分页查询科室信息
    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        //创建一个 Pageable 对象,设置当前页和每页记录数
        //page从0开始，因此-1
        Pageable pageable = PageRequest.of(page-1,limit);
        //创建一个 Example 对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //属性复制
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);
        Example<Department> example = Example.of(department,matcher);

        Page<Department> departmentPage = departmentRepository.findAll(example, pageable);
        return departmentPage;
    }

    @Override
    public void deleteDepartment(String hoscode, String depcode) {
        //根据医院编号和科室编号查询
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department != null){
            departmentRepository.deleteById(department.getId());
        }
    }
}
