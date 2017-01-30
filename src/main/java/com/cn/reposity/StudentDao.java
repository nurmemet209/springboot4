package com.cn.reposity;

import com.cn.entity.Student;
import com.cn.projection.StudentPro;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 1/30/2017.
 */
public interface StudentDao extends CrudRepository<Student,Long> {

    StudentPro findById(Long id);

}
