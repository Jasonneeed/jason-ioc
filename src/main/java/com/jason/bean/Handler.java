package com.jason.bean;

import java.lang.reflect.Method;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:42
 * @modify 2020/4/2 14:42
 */
public class Handler {

    private Class<?> controllerClass;

    private Method requestMethod;

    public Handler(Class<?> controllerClass, Method requestMethod) {
        this.controllerClass = controllerClass;
        this.requestMethod = requestMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getRequestMethod() {
        return requestMethod;
    }
}
