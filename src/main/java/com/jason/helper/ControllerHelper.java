package com.jason.helper;

import com.jason.annotation.RequestMapping;
import com.jason.bean.Handler;
import com.jason.bean.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:43
 * @modify 2020/4/2 14:43
 */
public final class ControllerHelper {
    private static final Map<Request, Handler> map = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> aClass : controllerClassSet) {
                Method[] methods = aClass.getMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mapping = requestMapping.value();
                            String requestMethod = requestMapping.method().toUpperCase();
                            Request request = new Request(requestMethod, mapping);
                            Handler handler = new Handler(aClass, method);
                            map.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestMapping) {
        Request request = new Request(requestMethod, requestMapping);
        return map.get(request);
    }
}
