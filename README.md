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
SpringBoot中Spring Data Jpa的使用非常简单，在你的gradle文件中只添加以下内容即可
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
