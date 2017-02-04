package com.cn.reposity;

import com.cn.entity.Cat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2/4/2017.
 */
public interface CatDao extends CrudRepository<Cat,Long> {

}
