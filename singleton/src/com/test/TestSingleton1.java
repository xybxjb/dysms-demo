package com.test;

import com.single.Singleton1;

/**
 * @author 加鑫宇
 * @date 2020-11-17 11:03
 */
public class TestSingleton1 {

    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
    }

}
