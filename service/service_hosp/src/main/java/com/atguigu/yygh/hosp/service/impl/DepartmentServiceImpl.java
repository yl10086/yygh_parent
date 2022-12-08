package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.DepartmentRepository;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    //根据医院编号，查询医院所有科室列表
    @Override
    public List<DepartmentVo> findDepartmentTree(String hoscode) {
        //1、创建一个list集合，用于最终数据封装
        List<DepartmentVo> result = new ArrayList<>();
        //2、根据医院编号，查询医院所有科室信息
        Department departmentQuery = new Department();
        departmentQuery.setHoscode(hoscode);
        Example<Department> example = Example.of(departmentQuery);
        List<Department> departmentList = departmentRepository.findAll(example);
        //3、根据大科室编号 bigcode 进行分组，获取每个大科室里面的下级子科室
        Map<String, List<Department>> departmentMap = departmentList.stream()
                .collect(Collectors.groupingBy(Department::getBigcode));
        //遍历Map集合 departmentMap
        for (Map.Entry<String,List<Department>> entry : departmentMap.entrySet()) {
            //大科室编号
            String bigcode = entry.getKey();
            //大科室编号对应的全部数据
            List<Department> departmentList1 = entry.getValue();

            //封装大科室
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(bigcode);
            departmentVo.setDepname(departmentList1.get(0).getBigname());

            //封装小科室
            List<DepartmentVo> children = new ArrayList<>();
            for (Department department : departmentList1){
                DepartmentVo departmentVo1 = new DepartmentVo();
                //将实体类Department中的数据进行转化到DepartmentVo类
                departmentVo1.setDepcode(department.getDepcode());
                departmentVo1.setDepname(department.getDepname());
                //将转换后的数据封装到list集合中
                children.add(departmentVo1);
            }
            //4、把小科室list集合放到大科室children里去
            departmentVo.setChildren(children);
            //5、将数据返回给最终封装的result中去
            result.add(departmentVo);
        }
        return result;
    }

    //根据科室编号、医院编号，查询科室名称
    @Override
    public String getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department != null) {
            return department.getDepname();
        }
        return null;
    }
}
