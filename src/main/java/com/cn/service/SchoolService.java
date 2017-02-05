package com.cn.service;

import com.cn.entity.School;
import com.cn.entityspec.SchoolSpec;
import com.cn.reposity.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 1/31/2017.
 */
@Service
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;

    public School add(School school){
      return   schoolDao.save(school);
    }

    public Page<School> findAll(School school, Pageable pageable){
        return schoolDao.findAll(SchoolSpec.getEmptyOne(),pageable);
    }
}
