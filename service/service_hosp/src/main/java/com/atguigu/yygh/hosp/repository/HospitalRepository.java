package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YeLei
 * @Date 2022/11/13 13:11
 * @Version 1.0
 */

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    //获取医院编号
    Hospital getHospitalByHoscode(String hoscode);
}
