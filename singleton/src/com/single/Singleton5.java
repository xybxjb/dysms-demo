package com.single;

/**
 * @author 加鑫宇
 * @date 2020-11-17 11:50
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {

    }

    /**
     * 线程安全
     *
     * @author User
     * @Date 2020-11-17 11:55:49
     **/
    public static Singleton5 getInstance() throws InterruptedException {
        if (instance == null) {
            //加锁 不考虑性能
            synchronized (Singleton5.class) {

                if (instance == null) {
                    Thread.sleep(100);
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}



