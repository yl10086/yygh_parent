package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.ScheduleRepository;
import com.atguigu.yygh.hosp.service.ScheduleService;
import com.atguigu.yygh.model.hosp.Schedule;
import com.atguigu.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/14 21:07
 * @Version 1.0
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数 map 集合转换成 Department 对象
        String mapString = JSONObject.toJSONString(paramMap);
        Schedule schedule = JSONObject.parseObject(mapString, Schedule.class);
        //根据医院编号 和 排版编号 进行查询
        Schedule scheduleExist = scheduleRepository
                .getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getHosScheduleId());

        //存在,进行修改操作
        if (scheduleExist != null){
            schedule.setId(scheduleExist.getId());
            schedule.setUpdateTime(new Date());
            schedule.setStatus(1);
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }else { //不存在,进行添加操作
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setStatus(1);
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }
    }

    @Override
    public Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        //创建一个 Pageable 对象,设置当前页和每页记录数
        //page从0开始，因此-1
        Pageable pageable = PageRequest.of(page-1,limit);
        //创建一个 Example 对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //属性复制
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo,schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        Example<Schedule> example = Example.of(schedule,matcher);

        Page<Schedule> schedulePage = scheduleRepository.findAll(example, pageable);
        return schedulePage;
    }

    @Override
    public void deleteSchedule(String hoscode, String hosScheduleId) {
        Schedule schedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (schedule != null){
            scheduleRepository.deleteById(schedule.getId());
        }
    }
}
