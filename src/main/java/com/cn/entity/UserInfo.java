package com.cn.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 1/23/2017.
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String address;
    private String tel;
    private String age;
    //抓取方式默认是EAGER，Lazy是懒加载 意思第一次查询的时候不查group 这个属性 当用到这个实体类的group字段的get方法是才去数据库读取
    //这个字段,是Spring Jpa的一个特性，是因为有的时候我们原本就不想查出这个group信息而只是想查出UserInfo的基本信息，所以算是一个性能的保障。
    //而FetchType.EAGER顾名词义就是立刻查询的意思，就跟第一次查询的时候跟UserInfo的其他属性一起查出来
    //但是就算是一次性查出来（FetchType.EAGER），Spring 也不用一条sql来查出来而用多个sql查询，拼出结果
    //UserInfo->Group  是多对一的关系
    //JoinColumn 是user_info表里的字段
    @ManyToOne(fetch =FetchType.EAGER,optional = false)
    @JoinColumn(name = "group_id")//user_info表中关联user_group表的字段名称,如果此处不加@JoinColumn jpa会在user_info表里面寻找group_id(下面的group字段后面_id)如果找不到报错
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }




    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
