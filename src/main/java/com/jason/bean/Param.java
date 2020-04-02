package com.jason.bean;

import com.jason.utils.CastUtil;

import java.util.Map;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 14:57
 * @modify 2020/4/2 14:57
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> map) {
        this.paramMap = map;
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(paramMap.get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(paramMap.get(name));
    }

    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(paramMap.get(name));
    }

    public String getString(String name) {
        return CastUtil.castString(paramMap.get(name));
    }

    public Object getObject(String name) {
        return paramMap.get(name);
    }

    public Map<String, Object> getParams() {
        return paramMap;
    }
}
