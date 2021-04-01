package com.test;

import com.single.Singleton2;

/**
 * @author 加鑫宇
 * @date 2020-11-17 11:05
 */
public class TestSingleton2 {
    public static void main(String[] args) {
        Singleton2 s = Singleton2.INSTANCE;
        System.out.println(s);
    }
}
