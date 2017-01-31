package com.cn.reposity;

import com.cn.entity.Car;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Administrator on 1/30/2017.
 */
public interface CarDao extends CrudRepository<Car,Long> {

    /**
     * 通过procedureName调用存储过程(这里的方法名这是任意的)
     * 如果函数参数列表名称不跟存储过程中定义的名称一样得用@Param注解指明相应参数在数据库存储过程中对应的参数名称，此处因为
     * 跟数据库存储过程名称一样所以不用加@Param
     * @param a
     * @param b
     * @return
     */
    @Procedure(procedureName  = "test")
    int anyFunctionName( Integer a, Integer b);


    /**
     * 这里的test是存储过程名称,方法是任意的
     * 如果函数参数列表名称不跟存储过程中定义的名称一样得用@Param注解指明相应参数在数据库存储过程中对应的参数名称，此处因为
     * 跟数据库存储过程名称一样所以不用加@Param
     * @param a
     * @param b
     * @return
     */
    @Procedure("test")
    int anyFunctionName1(Integer a,  Integer b);

    /**
     * 根据方法名调用,这里的方法名是从NamedStoredProcedureQuery的那么name属性来的（Car.procedure1）
     * 函数参数列表必须用@Param修饰
     * @param a
     * @param b
     * @return
     */
    @Procedure
    int procedure1(@Param("a") Integer a, @Param("b") Integer b);

    /**
     * 这里的方法名是任意的，参数列表必须用@Param修饰
     * @param a
     * @param b
     * @return
     */
    @Procedure(name ="Car.procedure1" )
    int anyFunctionName2(@Param("a") Integer a,@Param("b") Integer b) ;



}
