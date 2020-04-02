package com.jason.helper;

import com.jason.utils.ReflectionUtil;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:20
 * @modify 2020/4/2 14:20
 */
public final class BeanHelper {

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> aClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(aClass);
            beanMap.put(aClass, obj);
        }
    }

    //获取bean集合
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    //获取单个类型的实例对象
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) beanMap.get(cls);
    }
}
