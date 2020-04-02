package com.jason;

import com.jason.helper.BeanHelper;
import com.jason.helper.ClassHelper;
import com.jason.helper.ControllerHelper;
import com.jason.helper.IOCHelper;
import com.jason.utils.ClassUtil;

/**
 * 全局加载器
 *
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:53
 * @modify 2020/4/2 14:53
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {ClassHelper.class, BeanHelper.class, IOCHelper.class, ControllerHelper.class};
        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
