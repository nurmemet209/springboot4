package com.cn.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 1/30/2017.
 * @NamedStoredProcedureQuery 的name 由EntityManager维护的指向数据库存储过程名称的一个引用
 * procedureName是数据库中存储过程的名称,out参数是作为方法的返回值返回Dao层声明方法的时候
 * 不会在函数的参数列表出现，例如以下存储过程对应的Dao层函数为
 *   @Procedure
 *   int procedure1(@Param("a") Integer a, @Param("b") Integer b);
 */
@Entity
@Table(name = "car")
@NamedStoredProcedureQuery(name = "Car.procedure1", procedureName = "test", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "a", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "b", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "c", type = Integer.class)
})
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
