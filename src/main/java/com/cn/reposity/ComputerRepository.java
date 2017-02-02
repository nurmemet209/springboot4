package com.cn.reposity;

import com.cn.entity.Computer;

/**
 * Created by Administrator on 2/2/2017.
 */
public interface ComputerRepository {
     public Computer findByPrimaryKey(Long id);
}
