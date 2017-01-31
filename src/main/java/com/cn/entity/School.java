package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 1/31/2017.
 */
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double score;
    private Boolean  is211;

    public Boolean getIs211() {
        return is211;
    }

    public void setIs211(Boolean is211) {
        this.is211 = is211;
    }



    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

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
