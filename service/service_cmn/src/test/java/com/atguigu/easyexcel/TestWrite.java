package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import java.util.ArrayList;

/**
 * @Author YeLei
 * @Date 2022/11/06 12:17
 * @Version 1.0
 */
public class TestWrite {
    public static void main(String[] args) {
        //写入测试数据
        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("jack" + i);
            list.add(userData);
        }

        //设置excel路径和文件名称
        String filename = "D:\\SSM\\1026\\excel\\01.xlsx";

        //调用方法实现写操作
        EasyExcel.write(filename, UserData.class).sheet("用户表").doWrite(list);

    }
}
