package com.cn.service;

import com.cn.entity.School;
import com.cn.reposity.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by Administrator on 1/31/2017.
 */
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;



}
