# Spring Data Jpa

### 什么是Spring Data?  
[Spring Data](http://projects.spring.io/spring-data/)’s mission is to provide a familiar and consistent, Spring-based programming model for data access while
still retaining the special traits of the underlying data store. It makes it
easy to use data access technologies, relational and non-relational
databases, map-reduce frameworks, and cloud-based data services. This
is an umbrella project which contains many subprojects that are specif
ic to a given database. The projects are developed by working toget
her with many of the companies and developers that are behind these exciting
technologies.  
Spring Data 的目标是为数据存储提供方便，一致的，基于Spring编码方式的编程模型
它将操作关系型数据库和非关系型数据，云存储，map-reduce变得相当方便
这是一个大型项目，包含很多子项目，根据数据库的不同而不同，这些项目会从事相关领域的
一些大型公司或该领域的一些顶级专家维护并开发.  
目前已发布的项目有  
* Spring Data Commons  
* Spring Data JPA
* Spring Data KeyValue
* Spring Data LDAP
* Spring Data MongoDB
* Spring Data Gemfire
* Spring Data REST
* Spring Data Redis
* Spring Data for Apache Cassandra
* Spring Data for Apache Solr
* Spring Data Couchbase (community module)
* Spring Data Elasticsearch (community module)
* Spring Data Neo4j (community module)  

#### 什么是Spring Data Jpa?
[Spring Data JPA](http://projects.spring.io/spring-data-jpa/), part of the larger 
Spring Data family, makes it easy 
to easily implement JPA based repositories. This module 
deals with enhanced support for JPA based data access layers.
 It makes it easier to build Spring-powered applications that use 
 data access technologies.  
 Spring Data Jpa是 Spring Data 大家庭的一员，它把基于Jpa的库的实现变得相当
 容易，Spring Data Jpa 是为了简化Spring支持的项目的数据存储过程  
 
####SpringBoot中Spring Data Jpa的使用
SpringBoot中Spring Data Jpa([官方文档](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/))的使用非常简单，在你的gradle文件中只添加以下内容即可
 ```groovy
 compile('org.springframework.boot:spring-boot-starter-data-jpa')
```
包含Spring Data Jpa必须在 application.properties文件中配置数据源否则启动报错
```properties
#本例子中使用alibaba Druid作为数据库连接池,如果使用Tomcat jdbc作为数据库连接池可以不配置这个选项
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/srm
spring.datasource.username=root
spring.datasource.password=123456
```
使用alibaba Druid数据库连接池，gradle中还得引入Druid(该库的版本你可以先搜maven找到最新版本)
```groovy
compile group: 'com.alibaba', name: 'druid', version: '1.0.27'
```
之后在启动类添加注解
```java
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 1/23/2017.
 */
@SpringBootApplication(scanBasePackages = "com.cn")
@EnableJpaRepositories("com.cn.reposity")
@EntityScan("com.cn.entity")
public class SampleApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SampleApplication.class);
    }
    public static void main(String[] args){
        SpringApplication.run(SampleApplication.class,args);
    }
}

```
Dao层
```java
package com.cn.reposity;

import com.cn.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Created by Administrator on 1/23/2017.
 */
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    UserInfo findByUserName(String name);
    List<UserInfo> findAll();
}

```
Service层
```java
package com.cn.service;

import com.cn.entity.UserInfo;
import com.cn.reposity.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 1/23/2017.
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    public UserInfo addUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }
}

```
Controller层
```java
package com.cn.controller;

import com.cn.entity.UserInfo;
import com.cn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 1/23/2017.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("add")
    public String  add(UserInfo userInfo){
        userInfo=new UserInfo();
        userInfo.setUserName("alim");
        userInfo.setAddress("和田");
        userInfo.setTel("121212121");
        userInfoService.addUserInfo(userInfo);
        return "success";
    }

    @ResponseBody
    @RequestMapping("findAll")
    public List<UserInfo> findAll(UserInfo userInfo){
        return userInfoService.findAll();
    }

}

```
效果  
![](screenshoot/1.png)

####Spring data jpa 查询

([官方文档](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods))  
* 根据方法名查询
* 使用@Query查询
* 使用@Quey注解的时候实体类必须用@Table(name="表明")注解来配置映射,发现不用@Query的时候用@Entity(name="表名")注解来配置映射不报错
，但是用@Query就必须用@Table注解来配置映射
* @Query+ @Modifying+ @Transactional 如果是更新语句必须得加上@Modifying注解和@Transactional注解,注意返回值，
是int类型，是本次操作影响的行数
```java
package com.cn.reposity;

import com.cn.entity.UserInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    /**
     * Sort 排序,Sort的构造  new Sort("实体类字段名"),这种排序只能跟@Query一起使用
     * @param address
     * @param sort
     * @return
     */
    @Query("select u from UserInfo u where  u.address=?1")
    List<UserInfo> findByAddressAndSort(String address,Sort sort);

    /**
     * @Query +@param查询
     * @param userName
     * @param address
     * @return
     */
    @Query("select u from UserInfo u where  u.userName=:name and u.address=:add")
    List<UserInfo> findByNameAndAddress(@Param("name")String userName, @Param("add")String address);

    /**
     * @Query+ native sql 查询
     * @param userName
     * @return
     */
    @Query(value = "select u.* from user_info u where u.user_name=?1",nativeQuery = true)
    List<UserInfo> queryNative(String userName);

    /**
     * @Query+ @Modifying+ @Transactional 如果是更新语句必须得加上@Modifying注解和@Transactional注解,注意返回值，是int类型，是本次操作影响的行数
     * @return
     */
    @Modifying
    @Transactional
    @Query("update UserInfo u set u.userName=?2 where  u.userName=?1")
    int udpateByUserName(String userNameOld,String userNameNew);


}

```



####使用@CreatedBy,@CreatedDate注解
* 启动类添加@EnableJpaAuditing注解
```java
package com.cn.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 1/23/2017.
 */
@SpringBootApplication(scanBasePackages = "com.cn")
@EnableJpaRepositories("com.cn.reposity")
@EntityScan("com.cn.entity")
@EnableJpaAuditing
public class SampleApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SampleApplication.class);
    }
    public static void main(String[] args){
        SpringApplication.run(SampleApplication.class,args);
    }
}

```
* 先实现implements AuditorAware<T>  
让任意一个被Spring系统扫描到的类实现以上接口，是用户实体类，本例子中UserInfo,本例子中UserInfoService实现该接口
以下只是模拟数据，实际应用当中只要你返回一个当前用户对象就可以
```java
package com.cn.service;

import com.cn.entity.UserInfo;
import com.cn.reposity.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 1/23/2017.
 */
@Service
@Transactional
public class UserInfoService implements AuditorAware<UserInfo>{

    @Autowired
    UserInfoDao userInfoDao;

    /**
     * 添加
     * 如果userInfo存在Id那么更新，不存在添加
     * @param userInfo
     * @return
     */
    public UserInfo addUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    /**
     * 查询全部
     * @return
     */
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    public  List<UserInfo> findByUserName(String userName){
        return userInfoDao.findByUserName(userName);
    }


    @Override
    public UserInfo getCurrentAuditor() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("nurmemet");
        userInfo.setId(8L);
        return userInfo;
    }
}

```
* 添加@EntityListeners(AuditingEntityListener.class)注解
还有注意字段名称实体类里面是createUser 但在表里面这个字段是create_user_id
```java
package com.cn.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 1/26/2017.
 */
@Entity
@Table(name = "pro_brand")
@EntityListeners(AuditingEntityListener.class)
public class Brand {


    @Id
    @GeneratedValue
    private Long brandId;
    private String brandName;
    private String brandLogo;
    private Long brandClassifyId;

    public String getBrandKeyword() {
        return brandKeyword;
    }

    public void setBrandKeyword(String brandKeyword) {
        this.brandKeyword = brandKeyword;
    }

    private String brandKeyword;
    private String brandIntroduce;
    private String brandResponsible;

    @CreatedDate
    private Date createTime;

    public UserInfo getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UserInfo createUser) {
        this.createUser = createUser;
    }

    @CreatedBy
    @OneToOne
    private UserInfo createUser;
    private Integer state;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public Long getBrandClassifyId() {
        return brandClassifyId;
    }

    public void setBrandClassifyId(Long brandClassifyId) {
        this.brandClassifyId = brandClassifyId;
    }


    public String getBrandIntroduce() {
        return brandIntroduce;
    }

    public void setBrandIntroduce(String brandIntroduce) {
        this.brandIntroduce = brandIntroduce;
    }

    public String getBrandResponsible() {
        return brandResponsible;
    }

    public void setBrandResponsible(String brandResponsible) {
        this.brandResponsible = brandResponsible;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}

```
对应的数据库表  
![](screenshoot/2.png)
