package com.cn.reposity;

import com.cn.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Created by Administrator on 1/23/2017.
 * 官方文档 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**
     * 根据属性名称查询
     * @param name
     * @return
     */
    List<UserInfo> findByUserName(String name);

    /**
     * 通过属性名称查询
     * @param userName
     * @param address
     * @return
     */
    List<UserInfo> findByUserNameAndAddress(String userName,String address);
    /**'
     * 多个属性名称查询
     * @param userName
     * @param address
     * @param tel
     * @return
     */
    List<UserInfo> findByUserNameAndAddressAndTel(String userName,String address,String tel);


    /**
     * 查询全部
     * @return
     */
    List<UserInfo> findAll();

    /**
     * Or 查询
     * @param userName
     * @param tel
     * @return
     */
    List<UserInfo> findByUserNameOrTel(String userName ,String tel);

    /**
     * Between 查询
     * @param start
     * @param en
     * @return
     */
    List<UserInfo> findByAgeBetween(int start,int en);

    /**
     * lessthan 查询
     * @param age
     * @return
     */
    List<UserInfo> findByAgeLessThan(int age);

    /**
     * greaterThan 查询
     * @param age
     * @return
     */
    List<UserInfo> findByAgeGreaterThan(int age);

    /**
     * isNull查询
     * @return
     */
    List<UserInfo> findByAgeIsNull();

    /**
     * NotNull查询
     * @return
     */
    List<UserInfo> findByAgeNotNull();

    /**
     * like查询
     * @param userName
     * @return
     */
    List<UserInfo> findByUserNameLike(String userName);

    /**
     * NotLike查询
     * @param userName
     * @return
     */
    List<UserInfo> findByUserNameNotLike(String userName);

    /**
     * OrderBy Desc
     * @param userName
     * @return
     */
    List<UserInfo> findByAddressOrderByIdDesc(String userName);

    /**
     * OrderBy Asc
     * @param userName
     * @return
     */
    List<UserInfo> findByAddressOrderByIdAsc(String userName);

    /**
     * Not查询
     * @param age
     * @return
     */
    List<UserInfo> findByAgeNot(int age);

    /**
     * In 查询
     * @param ageList
     * @return
     */
    List<UserInfo> findByAgeIn(List<Integer> ageList);

    /**
     * NotIn 查询
     * @param ageList
     * @return
     */
    List<UserInfo> findByAgeNotIn(List<Integer> ageList);

    /**
     * 使用@Quey注解的时候实体类必须用@Table(name="表明")注解来配置映射,发现不用@Query的时候用@Entity(name="表名")注解来配置映射不报错，但是用@Query就必须用@Table注解来配置映射
     * @param userName
     * @return
     */
    @Query("select u from UserInfo u where  u.userName=?1")
    List<UserInfo> queryByUserName(String userName);


}
