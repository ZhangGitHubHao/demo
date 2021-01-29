package com.example.study.analysisexcel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存池，主要用于不同线程反射获取的Methods，减少相同线程反射执行次数，
 * 减轻应用的负载、提高执行效率。我这里是基于LinkedHashMap实现的LRU缓存，
 * 你也可以用数组或者其他方式实现该算法。以下代码逻辑如果不能理解的可以先去了解LinkedHashSet的源码。
 *
 * @author zhangh
 * @date 2019/10/21
 */
public class LRUCache {
    private  static  final Logger LOGGER= LoggerFactory.getLogger(LRUCache.class);
    //缓存容量
    private static final int cacheSize = 10;

    private static final Map<Integer, Method[]> cacheMap = new LinkedHashMap<Integer, Method[]>((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer,Method[]> eldest){

            return  size()> cacheSize;

        }
    };

    /**
     * 设置缓存
     * @param key
     * @param methods
     * @return boolean
     */
    public static boolean set (Integer key,Method [] methods) throws LRUCacheException {
        try {
            cacheMap.put(key,methods);
            return true;
        }
        catch ( Exception e ){
            throw new LRUCacheException("Set LRU缓存异常！");
        }
    }

    /**
     * 获取缓存的Method
     * @param key
     * @return Method
     */
    public static Method[] get(Integer key) throws LRUCacheException {
        Method[] methods=null;
        try {
            methods=cacheMap.get(key);
        }catch ( Exception e ){
            throw new LRUCacheException("Get LRU缓存异常！{}");
        }
        return methods;
    }
}
