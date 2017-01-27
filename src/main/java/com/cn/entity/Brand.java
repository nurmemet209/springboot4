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
