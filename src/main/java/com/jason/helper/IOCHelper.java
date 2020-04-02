package com.jason.helper;


import com.jason.annotation.Autowired;
import com.jason.utils.ReflectionUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:26
 * @modify 2020/4/2 14:26
 */
public final class IOCHelper {

    //自动注入所有bean的autowired属性。
    static {
        //获取所有bean映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            //遍历所有映射
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                //从映射中取出所有bean类和bean的实例
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();

                //获取bean类定义的所有成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        //查看需自动注入的属性
                        if (field.isAnnotationPresent(Autowired.class)) {
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
