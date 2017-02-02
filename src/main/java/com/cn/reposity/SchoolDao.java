package com.cn.reposity;

import com.cn.entity.School;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 1/31/2017.
 */
public interface SchoolDao extends CrudRepository<School,Long> ,JpaSpecificationExecutor {



}
