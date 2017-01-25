package com.cn.service;

import com.cn.entity.UserInfo;
import com.cn.reposity.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 1/23/2017.
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    /**
     * 添加
     * 如果userInfo存在Id那么更新，不存在添加
     * @param userInfo
     * @return
     */
    public UserInfo addUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    /**
     * 查询全部
     * @return
     */
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    public  List<UserInfo> findByUserName(String userName){
        return userInfoDao.findByUserName(userName);
    }


}
