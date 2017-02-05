package com.cn.controller;

import com.cn.entity.School;
import com.cn.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2/5/2017.
 */
@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @ResponseBody
    @GetMapping("add")
    public String add(School school){
        schoolService.add(school);
        return "success";
    }

    @ResponseBody
    @GetMapping("findAll")
    public Page<School> findAll(School school , Pageable pageable){

        System.out.println("test");
       return schoolService.findAll(school,pageable);

    }
}
