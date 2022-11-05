package com.atguigu.yygh.cmn.service;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:26
 * @Version 1.0
 */
public interface DictService extends IService<Dict> {

    List<Dict> findChildData(Long id);
}
