package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Schedule;
import com.atguigu.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/14 21:07
 * @Version 1.0
 */
public interface ScheduleService {

    //上传排班接口
    void save(Map<String, Object> paramMap);

    //分页查询排班
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void deleteSchedule(String hoscode, String hosScheduleId);
}
