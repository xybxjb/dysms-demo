package com.test;

import com.single.Singleton5;

import java.util.concurrent.*;

/**   线程安全方式
 * @author 加鑫宇
 * @date 2020-11-17 11:53
 */
public class TestSingleton5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
/**
 * 匿名内部类方式
 *  多线程
 *@author User
 *@Date 2020-11-17 11:42:05
 * @param args
 **/
        Callable<Singleton5> c = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton5> f1 = es.submit(c);
        Future<Singleton5> f2 = es.submit(c);

        Singleton5 s1 = f1.get();
        Singleton5 s2 = f2.get();
        //true
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s1);
        es.shutdown();
    }
}
