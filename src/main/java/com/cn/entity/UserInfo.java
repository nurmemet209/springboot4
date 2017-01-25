package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
