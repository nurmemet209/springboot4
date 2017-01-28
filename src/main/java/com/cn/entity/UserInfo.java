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
    //抓取方式默认是Lazy
    //UserInfo->Group  是多对一的关系
    //JoinColumn 是user_info表里的字段
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "group_id")
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
