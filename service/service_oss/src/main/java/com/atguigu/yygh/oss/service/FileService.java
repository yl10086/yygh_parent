package com.atguigu.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author YeLei
 * @Date 2022/12/19 15:39
 * @Version 1.0
 */
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);
}
