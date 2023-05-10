package com.hy.fourseas.common;


/**
 * 基于ThreadLacal封装的工具类，用于保存和获取当前登录用户ID
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentID(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentID(){
        return threadLocal.get();
    }

}
