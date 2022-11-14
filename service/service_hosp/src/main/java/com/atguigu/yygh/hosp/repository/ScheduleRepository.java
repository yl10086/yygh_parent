package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YeLei
 * @Date 2022/11/14 21:06
 * @Version 1.0
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    //获取医院编号和排版编号
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

}
