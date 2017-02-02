package com.cn.reposity;

import com.cn.entity.Author;
import com.cn.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 2/2/2017.
 */
@Repository
public class ComputerRepositoryImpl implements ComputerRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Computer findByPrimaryKey(Long id) {
        return entityManager.find(Computer.class,id);

    }
}
