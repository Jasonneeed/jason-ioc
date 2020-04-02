package com.jason.helper;

import com.jason.annotation.Controller;
import com.jason.annotation.Service;
import com.jason.utils.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:00
 * @modify 2020/4/2 14:00
 */
public final class ClassHelper {

    //用于存放所加载的类
    private static final Set<Class<?>> classSet;

    static {
        String basePackage = ConfigHelper.getBasePackage();
        classSet = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return classSet;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        for (Class<?> aClass : classSet) {
            if (aClass.isAnnotationPresent(Service.class)) {
                serviceSet.add(aClass);
            }
        }
        return serviceSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> controllerSet = new HashSet<Class<?>>();
        for (Class<?> aClass : classSet) {
            if (aClass.isAnnotationPresent(Controller.class)) {
                controllerSet.add(aClass);
            }
        }
        return controllerSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanSet = new HashSet<Class<?>>();
        beanSet.addAll(getServiceClassSet());
        beanSet.addAll(getControllerClassSet());
        return beanSet;
    }
}
