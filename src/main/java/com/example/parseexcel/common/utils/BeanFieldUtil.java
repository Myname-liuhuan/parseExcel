package com.example.parseexcel.common.utils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * bean中字段操作类
 */
public class BeanFieldUtil {


    /**
     * 设置一般情况下插入需要的五个字段，如果不存在就给默认值
     * 
     * @param <T>
     * @param bean
     * @return
     */
    public static <T> T setInsertDefaultValue(T bean){
        if(bean != null){
            Date date = new Date();
            Field createTimeField;
            try {
                createTimeField = bean.getClass().getField("createTime");
                if(createTimeField != null && createTimeField.get(bean) == null){
                    createTimeField.set(bean, date);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
    
}
