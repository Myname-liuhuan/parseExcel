package com.example.parseexcel.common.handler;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.parseexcel.common.constant.SystemConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * myabtis支持在实体类字段加注解标明字段在insrt,update等阶段需要的操作
 * 在这里自定义操作逻辑
 * 实现createTime,updateTime,createUser,updateUser等公共字段的自动填充功能
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
     
        //这里先默认给-1，等账户体系搭建起来后，应该是优先取当前登录用户id，没有才给-1
        this.setFieldValByName("createUser", -1L, metaObject);

        this.setFieldValByName("delFlag", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
     
        //这里先默认给-1，等账户体系搭建起来后，应该是优先取当前登录用户id，没有才给-1
        this.setFieldValByName("updateUser", -1L, metaObject);
    }
      
    
}
