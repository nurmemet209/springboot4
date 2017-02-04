package com.cn.app;

import com.cn.reposity.BaseRepositoryCustom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 1/23/2017.
 */
@SpringBootApplication(scanBasePackages = "com.cn")
@EnableJpaRepositories(basePackages = "com.cn.reposity")
@EntityScan("com.cn.entity")
@EnableJpaAuditing
public class SampleApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SampleApplication.class);
    }
    public static void main(String[] args){
        SpringApplication.run(SampleApplication.class,args);
    }
}
