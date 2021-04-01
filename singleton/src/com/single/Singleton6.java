package com.single;

/** 懒汉式的优化写法
 *
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而出初始化，它是要单独去加载和初始化的。
 * 因为在内部类加载和初始化时，创建的，线程安全；
 * @author 加鑫宇
 * @date 2020-11-17 12:01
 */
public class Singleton6 {
    private Singleton6 (){

    }
    private static class Inner{
        private static  Singleton6 INSTANCE =new Singleton6();
    }
    private static Singleton6 getInstance(){
        return Inner.INSTANCE;
    }
}
