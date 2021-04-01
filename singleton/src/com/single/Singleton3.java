package com.single;

/** 饿汉式
 *  静态代码块
 * @author 加鑫宇
 * @date 2020-11-17 11:07
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    static {
        INSTANCE=new Singleton3();
    }
    private Singleton3(){

    }
}
