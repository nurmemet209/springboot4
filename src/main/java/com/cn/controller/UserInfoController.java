package com.cn.controller;

import com.cn.entity.UserInfo;
import com.cn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 1/23/2017.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("add")
    public String  add(UserInfo userInfo){
        userInfoService.addUserInfo(userInfo);
        return "success";
    }

    @ResponseBody
    @RequestMapping("findAll")
    public List<UserInfo> findAll(UserInfo userInfo){


        return userInfoService.findAll();
    }

}
