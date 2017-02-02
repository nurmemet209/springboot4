package com.cn.service;

import com.cn.entity.Person;
import com.cn.reposity.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2/2/2017.
 */
@Service
public class PersonService {

    @Autowired
    PersonDao personDao;


    /**
     * Example 查询
     * @param probe
     * @return
     */
    public Iterable<Person> findAll(Person probe){
       return  personDao.findAll(Example.of(probe));
    }

    /**
     * 指定匹配规则,默认情况下它会匹配传进来的person参数的所有属性，通过ExampleMatcher你可以指定匹配规则，大小写之类的
     * @param probe
     * @param matcher
     * @return
     */
    public Iterable<Person> findAll(Person probe, ExampleMatcher matcher){
        return  personDao.findAll(Example.of(probe,matcher));
    }

}
