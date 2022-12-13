package com.atguigu.yygh.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author YeLei
 * @Date 2022/12/12 16:34
 * @Version 1.0
 */
@Component
public class EmailMsg implements InitializingBean {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    public static String EMAIL_USERNAME;
    public static String EMAIL_PASSWORD;
    public static String EMAIL_HOST;

    @Override
    public void afterPropertiesSet() {
        EMAIL_HOST = host;
        EMAIL_USERNAME =username;
        EMAIL_PASSWORD =password;
    }
}
