package com.cn.uk.common.utils;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
    private static ConcurrentHashMap<String, Object> map;

    public synchronized static ConcurrentHashMap<String, Object> getMap(){
        if(map==null){
            map=new ConcurrentHashMap<String, Object>();
        }

        return map;
    }

    public synchronized static <T> T getItem(String key, Class<T> clazz){
        if(map == null || !map.containsKey(key)){
            return null;
        }

        return (T) map.get(key);
    }

    public synchronized static void putItem(String key, Object item){
        ConcurrentMap.getMap().put(key, item);
    }
}
