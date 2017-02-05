package com.cn.reposity;

import com.cn.entity.Mouse;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2/5/2017.
 */
public interface MouseDao extends CrudRepository<Mouse,Long>{

}
