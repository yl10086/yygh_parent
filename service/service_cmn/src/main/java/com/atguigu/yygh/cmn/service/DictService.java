package com.atguigu.yygh.cmn.service;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2022/10/26 18:26
 * @Version 1.0
 */
public interface DictService extends IService<Dict> {

    //根据数据id查询子数据列表
    List<Dict> findChildData(Long id);

    //EasyExcel导出字典数据
    void exportDictData(HttpServletResponse response);

    //EasyExcel导入字典数据
    void importDictData(MultipartFile file);

    //根据dictcode和value条件查询||value条件查询
    String getDictName(String dictCode, String value);

    //根据dictCode获取下级节点
    List<Dict> findByDictCode(String dictCode);
}
