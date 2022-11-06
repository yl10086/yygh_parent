package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author YeLei
 * @Date 2022/11/06 12:49
 * @Version 1.0
 */
public class TestRead {

    public static void main(String[] args) {
        //需要读取的文件路径
        String fileName = "D:\\SSM\\1026\\excel\\01.xlsx";
        //读取文件
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
