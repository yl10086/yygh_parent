package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Schedule;
import com.atguigu.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import javax.xml.crypto.Data;
import java.util.List;
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

    //根据医院编号和科室编号,查询排班规则数据
    Map<String, Object> getRuleSchedule(Integer page, Integer limit, String hoscode, String depcode);

    //根据医院编号、科室编号和工作日期，查询排班详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
