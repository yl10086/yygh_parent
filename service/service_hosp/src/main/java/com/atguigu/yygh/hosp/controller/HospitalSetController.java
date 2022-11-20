package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.util.MD5;
import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:36
 * @Version 1.0
 * 方便复制粘贴访问
 * http:localhost:8201/admin/hosp/hospitalSet/findAll
 */

@Api(tags = "医院管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    @Autowired
    private HospitalSetServiceImpl hospitalSetService;

    @ApiOperation(value = "查询所有医院信息")
    @GetMapping("findAll")
    public Result findAll(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "逻辑删除一条医院信息")
    @ApiParam(name = "id",value = "医院id",required = true)
    @DeleteMapping("{id}")
    public Result deleteOne(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * @RequestBody(required = false)
     *      输出形式为JSON格式，
     *      required = false表示可以不传值,
     *      并且不能使用@GetMapping发送请求
    *  @param current
     * @param size
     * @param hospitalSetQueryVo
     * @return
     */
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("selectPage/{current}/{size}")
    public Result selectPage(@PathVariable Long current,
                             @PathVariable Long size,
                             @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建分页
        Page<HospitalSet> page = new Page<>(current,size);
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        //获取医院名称
        String hosname = hospitalSetQueryVo.getHosname();
        //获取医院编号
        String hoscode = hospitalSetQueryVo.getHoscode();
        if (!StringUtils.isEmpty(hosname)){
            //根据医院名称进行模糊查询
            queryWrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)) {
            //根据医院编号查询
            queryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页
        Page<HospitalSet> page1 = hospitalSetService.page(page, queryWrapper);
        return Result.ok(page1);
    }

    @ApiOperation(value = "添加医院信息")
    @PostMapping("addHospitalSet")
    public Result addHospitalSet(@RequestBody HospitalSet hospitalSet){
        //设置状态为1
        hospitalSet.setStatus(1);
        Random random = new Random();
        //MD5加密
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        boolean save = hospitalSetService.save(hospitalSet);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "查询表中一个医院信息")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id){
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation(value = "修改医院信息")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean update = hospitalSetService.updateById(hospitalSet);
        if(update){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量删除医院信息")
    @DeleteMapping("batchDeleteHospitalSet")
    public Result batchDeleteHospitalSet(@RequestBody List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "设置医院的状态：锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){
        //先通过数据库查询是否有这个id
        HospitalSet id1 = hospitalSetService.getById(id);
        //设置status
        id1.setStatus(status);
        //调用更新方法修改状态
        hospitalSetService.updateById(id1);
        return Result.ok();
    }

    @ApiOperation(value = "发送签名和密钥")
    @PostMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        //先通过数据库查询是否有这个id
        HospitalSet id1 = hospitalSetService.getById(id);
        String signKey = id1.getSignKey();
        String hoscode = id1.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }

}
