package com.cn.reposity;

import com.cn.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Created by Administrator on 2/2/2017.
 */
public interface PersonDao extends CrudRepository<Person,Long> ,QueryByExampleExecutor<Person> {

}
