package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YeLei
 * @Date 2022/11/14 18:53
 * @Version 1.0
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    //获取医院和科室的编号
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
