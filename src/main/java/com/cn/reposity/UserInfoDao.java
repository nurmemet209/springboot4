package com.cn.reposity;

import com.cn.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Created by Administrator on 1/23/2017.
 */
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    UserInfo findByUserName(String name);
    List<UserInfo> findAll();
}
