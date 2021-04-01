package com.single;

/**
 * 饿汉式 （不存在线程安全问题（因为java中的类加载））
 * 直接创建实例对象，不管你是否需要这个对象
 *   （1）构造器私有化
 *   （2）自行创建，并且用静态变量保存
 *   （3）向外提供这个实例
 *   （4）强调这是一个实例，我们可以用final修改
 *
 * @author 加鑫宇
 * @date 2020-11-17 10:55
 */
public class Singleton1 {
    public static final  Singleton1 INSTANCE = new Singleton1();
    private Singleton1(){

    }

}
