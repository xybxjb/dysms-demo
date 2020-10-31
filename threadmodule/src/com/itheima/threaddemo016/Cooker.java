package com.itheima.threaddemo016;

import com.itheima.threaddemo015.Desk;

import java.util.concurrent.ArrayBlockingQueue;

public class Cooker extends Thread {

    private ArrayBlockingQueue<String> bd;

    public Cooker(ArrayBlockingQueue<String> bd) {
        this.bd = bd;
    }
//    生产者步骤：
//            1，判断桌子上是否有汉堡包
//    如果有就等待，如果没有才生产。
//            2，把汉堡包放在桌子上。
//            3，叫醒等待的消费者开吃。

    @Override
    public void run() {
        while (true) {
            try {
                bd.put("汉堡包");
                System.out.println("厨师放入一个汉堡包");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
