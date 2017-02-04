package com.cn.reposity;

import com.cn.entity.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * Created by Administrator on 1/23/2017.
 * 官方文档 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    /**
     * 根据属性名称查询
     *
     * @param name
     * @return
     */
    List<UserInfo> findByUserName(String name);

    /**
     * 通过属性名称查询
     *
     * @param userName
     * @param address
     * @return
     */
    List<UserInfo> findByUserNameAndAddress(String userName, String address);

    /**
     * '
     * 多个属性名称查询
     *
     * @param userName
     * @param address
     * @param tel
     * @return
     */
    List<UserInfo> findByUserNameAndAddressAndTel(String userName, String address, String tel);


    /**
     * 查询全部
     *
     * @return
     */
    List<UserInfo> findAll();

    /**
     * Or 查询
     *
     * @param userName
     * @param tel
     * @return
     */
    List<UserInfo> findByUserNameOrTel(String userName, String tel);

    /**
     * Between 查询
     *
     * @param start
     * @param en
     * @return
     */
    List<UserInfo> findByAgeBetween(int start, int en);

    /**
     * lessthan 查询
     *
     * @param age
     * @return
     */
    List<UserInfo> findByAgeLessThan(int age);

    /**
     * greaterThan 查询
     *
     * @param age
     * @return
     */
    List<UserInfo> findByAgeGreaterThan(int age);

    /**
     * isNull查询
     *
     * @return
     */
    List<UserInfo> findByAgeIsNull();

    /**
     * NotNull查询
     *
     * @return
     */
    List<UserInfo> findByAgeNotNull();

    /**
     * like查询
     *
     * @param userName
     * @return
     */
    List<UserInfo> findByUserNameLike(String userName);

    /**
     * NotLike查询
     *
     * @param userName
     * @return
     */
    List<UserInfo> findByUserNameNotLike(String userName);

    /**
     * OrderBy Desc
     *
     * @param userName
     * @return
     */
    List<UserInfo> findByAddressOrderByIdDesc(String userName);

    /**
     * OrderBy Asc
     *
     * @param userName
     * @return
     */
    List<UserInfo> findByAddressOrderByIdAsc(String userName);


    /**
     * Not查询
     *
     * @param age
     * @return
     */
    List<UserInfo> findByAgeNot(int age);

    /**
     * In 查询
     *
     * @param ageList
     * @return
     */
    List<UserInfo> findByAgeIn(List<Integer> ageList);

    /**
     * NotIn 查询
     *
     * @param ageList
     * @return
     */
    List<UserInfo> findByAgeNotIn(List<Integer> ageList);

    /**
     * 使用@Quey注解的时候实体类必须用@Table(name="表明")注解来配置映射,发现不用@Query的时候用@Entity(name="表名")注解来配置映射不报错，但是用@Query就必须用@Table注解来配置映射
     *
     * @param userName
     * @return
     */
    @Query("select u from UserInfo u where  u.userName=?1")
    List<UserInfo> queryByUserName(String userName);

    /**
     * Sort 排序,Sort的构造  new Sort("实体类字段名"),这种排序只能跟@Query一起使用
     *
     * @param address
     * @param sort
     * @return
     */
    @Query("select u from UserInfo u where  u.address=?1")
    List<UserInfo> findByAddressAndSort(String address, Sort sort);

    /**
     * @param userName
     * @param address
     * @return
     * @Query +@param查询
     */
    @Query("select u from UserInfo u where  u.userName=:name and u.address=:add")
    List<UserInfo> findByNameAndAddress(@Param("name") String userName, @Param("add") String address);

    /**
     * @param userName
     * @return
     * @Query+ native sql 查询
     */
    @Query(value = "select u.* from user_info u where u.user_name=?1", nativeQuery = true)
    List<UserInfo> queryNative(String userName);

    /**
     * @return
     * @Query+ @Modifying+ @Transactional 如果是更新语句必须得加上@Modifying注解和@Transactional注解,注意返回值，是int类型，是本次操作影响的行数
     */
    @Modifying
    @Transactional
    @Lock(LockModeType.READ)
    @Query("update UserInfo u set u.userName=?2 where  u.userName=?1")
    int udpateByUserName(String userNameOld, String userNameNew);

    //使用top 或first控制查询数量
    List<UserInfo> queryFirst10ByUserName(String userName);
    Slice<UserInfo> findTop3ByUserName(String userName);
    List<UserInfo> findFirst10ByUserName(String userName);
    List<UserInfo> findTop10ByUserName(String userName);

    //返回stream
    @Query("select u from UserInfo u")
    Stream<UserInfo> findAllByCustomQueryAndStream();
    @Query("select u from UserInfo u")
    Stream<UserInfo> streamAllPaged(Pageable pageable);

    //异步查询
    @Async
    Future<UserInfo> findById(Long id);
    @Async
    CompletableFuture<UserInfo> findByAge(String age);
    @Async
    ListenableFuture<UserInfo> findByTel(String tel);

}
