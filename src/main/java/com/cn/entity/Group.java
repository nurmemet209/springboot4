package com.cn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 1/27/2017.
 */
@Entity
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //fetch 默认值是 FetchType.Lazy
    //Group->UserInfo 是一对多的关系 ，注意mappedBy是UserInfo实体类里面的字段名而不是user_info数据库表里面的字段名
    @OneToMany(mappedBy = "group",fetch =FetchType.EAGER)
    private List<UserInfo> members=new ArrayList<>();


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

    public List<UserInfo> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfo> members) {
        this.members = members;
    }


}
