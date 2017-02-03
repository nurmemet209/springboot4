package com.cn.reposity;

import com.cn.entity.Pen;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * Created by Administrator on 2/3/2017.
 */
@RepositoryDefinition(domainClass = Pen.class,idClass = Long.class)
public interface PenDao {

    Pen findById(Long id);
}
