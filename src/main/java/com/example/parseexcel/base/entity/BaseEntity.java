package com.example.parseexcel.base.entity;

import java.util.Date;

/**
 * 基础实体类，
 * 给定公共字段,
 * 因为是基础类，所以不使用第三方类
 */
public abstract class BaseEntity {
    
    public String createUser;

    public String updateUser;

    public Date createTime;

    public Date updateTime;

    public Integer delFlag;

    public BaseEntity() {
        
    }

    public BaseEntity(String createUser, String updateUser, Date createTime, Date updateTime, Integer delFlag) {
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }
    
    public void setDelFlag(Integer delFlag){
        this.delFlag = delFlag;
    }
    
}
