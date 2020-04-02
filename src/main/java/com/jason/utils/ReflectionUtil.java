package com.jason.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:08
 * @modify 2020/4/2 14:08
 */
public final class ReflectionUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建某个类的实例
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param o
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object o, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(o, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改某个成员变量
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set filed failure", e);
            throw new RuntimeException(e);
        }
    }

}
