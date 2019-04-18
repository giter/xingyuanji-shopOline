package com.shopoline.xingyuanji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = {"com.shopoline.xingyuanji.mapper"})
@EntityScan(basePackages= {"com.shopoline.xingyuanji.entity"})
public class XingyuanjiApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(XingyuanjiApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(XingyuanjiApplication.class, args);
    }
}

