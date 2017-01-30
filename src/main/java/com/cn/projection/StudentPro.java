package com.cn.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 1/30/2017.
 */
public interface StudentPro {
    /**
     * 实体类成员变量的get方法
     * @return
     */
    String getFirstName();

    /**
     * 也可以不是实体类成员变量的get方法，但是这个时候你必须得告诉spring系统该字段是匹配实体类的那个字段
     * 也可以是实体类多个字段拼出来之后的结果，如下
     * @return
     */
    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();

}
