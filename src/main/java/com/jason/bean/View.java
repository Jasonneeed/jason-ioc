package com.jason.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 15:02
 * @modify 2020/4/2 15:02
 */
public class View {

    private String path;
    private Map<String, Object> model;

    public View(String path){
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value){
        model.put(key,value);
        return this;
    }

    public String getPath(){
        return this.path;
    }

    public Map<String, Object> getModel(){
        return model;
    }
}
