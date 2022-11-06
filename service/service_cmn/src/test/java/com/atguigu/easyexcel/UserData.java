package com.atguigu.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author YeLei
 * @Date 2022/11/06 12:10
 * @Version 1.0
 */
@Data
public class UserData {

    @ExcelProperty(value = "序号")
    private int uid;

    @ExcelProperty(value = "用户名称")
    private String username;
}
