package com.single;

/**懒汉式(线程不安全（适用于单线程）），（线程安全（适用于多线程），静态内部类形式（适用于多线程））
 * （1）构造器私有化
 *  (2) 用一个静态变量保存这个唯一的实例
 *  (3) 提供一个静态方法，获取这个实例对象
 *
 * @author 加鑫宇
 * @date 2020-11-17 11:14
 */
public class Singleton4 {
    private static Singleton4 instance;
    private  Singleton4(){

    }
    public static Singleton4 getInstance() throws InterruptedException {
      if (instance == null){
          Thread.sleep(100);
          instance = new Singleton4();
      }
      return instance;
    }
}
