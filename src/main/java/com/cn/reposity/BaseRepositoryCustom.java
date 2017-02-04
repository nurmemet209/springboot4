package com.cn.reposity;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by Administrator on 2/4/2017.
 */
@NoRepositoryBean
public interface BaseRepositoryCustom<T, ID extends Serializable>
        extends PagingAndSortingRepository<T, ID> {

    void sharedCustomMethod(ID id);
}
