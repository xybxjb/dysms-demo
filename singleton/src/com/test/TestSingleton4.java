package com.test;

import com.single.Singleton4;

import java.util.concurrent.*;

/**
 * @author 加鑫宇
 * @date 2020-11-17 11:23
 */
public class TestSingleton4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
      /*  Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println(s1==s2);
        System.out.println(s1);
        System.out.println(s1);*/


/**
 * 匿名内部类方式
 *  多线程
 *@author User
 *@Date 2020-11-17 11:42:05
 * @param args
 **/
        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s1 = f1.get();
        Singleton4 s2 = f2.get();
        /**
         *  false(原因）：
         *   第一个线程进来判断为空，执行sleep（100） 完后cup让出来，这时第一个线程还没new
         *   第二个线程进来了 ，因为第一个没new，所以判断为空，
         *   第一个线程刚结束sleep，执行new，
         *   第二个线程稍后也sleep完，在执行一遍
         *所以存在线程安全问题，不只是sleep，没有也会有概率会创建2变
         *@author User
         *@Date 2020-11-17 11:43:05
         * @param args
         **/
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s1);
        es.shutdown();
    }
}
