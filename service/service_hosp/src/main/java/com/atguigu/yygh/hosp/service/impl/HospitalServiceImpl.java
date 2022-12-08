package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.cmn.client.DictFeignClient;
import com.atguigu.yygh.hosp.repository.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author YeLei
 * @Date 2022/11/13 13:14
 * @Version 1.0
 */

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数 map 集合转换成 Hospital 对象
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        //判断是否存在相同数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);
        //存在,进行修改操作
        if (hospitalExist != null){
            hospital.setId(hospital.getId());
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else { //不存在,进行添加操作
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        return hospital;
    }

    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //创建一个 Pageable 对象,设置当前页和每页记录数
        //page从0开始，因此-1
        Pageable pageable = PageRequest.of(page-1,limit);
        //创建一个 Example 对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //属性复制
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo,hospital);
        //创建对象
        Example<Hospital> example = Example.of(hospital,matcher);
        Page<Hospital> hospitalPage = hospitalRepository.findAll(example, pageable);
        //遍历进行医院等级封装
        hospitalPage.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });
        return hospitalPage;
    }

    //获取查询list集合，遍历进行医院等级封装
    private Hospital setHospitalHosType(Hospital hospital){
        //根据dictCode和value获取医院等级名称
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
        //查询 省 市 区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());
        
        hospital.getParam().put("fullAddress",provinceString + cityString + districtString);
        hospital.getParam().put("hostypeString",hostypeString);
        return hospital;
    }
    //更新医院上线状态
    @Override
    public void updateStatus(String id, Integer status) {
        //根据id查询医院信息
        Hospital hospital = hospitalRepository.findById(id).get();
        //修改状态值
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        //保存修改数据
        hospitalRepository.save(hospital);
    }

    //医院详情信息
    @Override
    public Map<String,Object> getHospById(String id) {
        Map<String,Object> result = new HashMap<>();
        //将医院信息再次进行封装
        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        result.put("hospital", hospital);
        //单独处理更直观
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;

    }

    //获取医院名称
    @Override
    public String getHospName(String hoscode) {
        Hospital hospitalByHoscode = hospitalRepository.getHospitalByHoscode(hoscode);
        if (hospitalByHoscode != null){
            return hospitalByHoscode.getHosname();
        }
        return null;
    }

    //根据医院名称查询
    @Override
    public List<Hospital> findByHosname(String hosname) {
        return hospitalRepository.getHospitalByHosnameLike(hosname);
    }
}
