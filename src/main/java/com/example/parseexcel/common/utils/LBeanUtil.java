package com.example.parseexcel.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyAccessor;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * bean工具类
 */
public class LBeanUtil {

    /**
     * 复制bean属性值
     * @param source
     * @param target
     */

    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }

        BeanUtils.copyProperties(source, target);

        // Get all fields from the source class, including inherited fields
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);

                // If the field is of type Date, handle it specifically
                if (value instanceof Date) {
                    // Copy the date value (you may want to clone the Date to avoid mutable issues)
                    Date dateValue = (Date) value;
                    PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(target);
                    accessor.setPropertyValue(field.getName(), new Date(dateValue.getTime()));
                } 
            } catch (IllegalAccessException | BeansException e) {
                e.printStackTrace();
            }
        }       
    }

    
}
