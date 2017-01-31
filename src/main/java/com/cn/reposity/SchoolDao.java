package com.cn.reposity;

import com.cn.entity.School;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Administrator on 1/31/2017.
 */
public interface SchoolDao extends CrudRepository<School,Long> ,JpaSpecificationExecutor {



}
