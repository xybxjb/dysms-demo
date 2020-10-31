package com.itheima.threaddemo016;

import com.itheima.threaddemo015.Desk;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie extends Thread {
    private ArrayBlockingQueue<String> bd;

    public Foodie(ArrayBlockingQueue<String> bd) {
        this.bd = bd;
    }

    @Override
    public void run() {
//        1，判断桌子上是否有汉堡包。
//        2，如果没有就等待。
//        3，如果有就开吃
//        4，吃完之后，桌子上的汉堡包就没有了
//                叫醒等待的生产者继续生产
//        汉堡包的总数量减一

        //套路:
        //1. while(true)死循环
        //2. synchronized 锁,锁对象要唯一
        //3. 判断,共享数据是否结束. 结束
        //4. 判断,共享数据是否结束. 没有结束
        while (true) {
            try {
                String take = bd.take();
                System.out.println("吃货将" + take + "拿出来吃了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
