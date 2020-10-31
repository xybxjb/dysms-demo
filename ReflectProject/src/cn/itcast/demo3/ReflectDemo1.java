package cn.itcast.demo3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *  Class类
 *     成员方法:
 *         public Method getMethod(String name, Class… params)
 *              返回一个Method对象，仅公共成员方法
 *
 *         public Method getDeclaredMethod(String, Class… params)
 *             返回一个Method对象，可获取私有成员方法
 *         public Method[] getMethods()  返回此类所有（不含私有）方法的数组
 *
 *  Method: 方法类, 用来表示所有的成员方法(对象)的.
 *     概述: 属于java.base模块下的 java.lang.reflect包下的类.
 *
 *     成员方法:
 *        public String getName();                            获取成员方法名
 *        public Object invoke(Object obj, Object… params)   调用指定方法,
 *                                                            obj表示该类的对象
 *                                                            params表示调用该方法所需的参数
 *        public void setAccessible(boolean flag);           是否开启暴力反射(true: 开启)
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws Exception {
       //需求: 通过反射获取Student类中的成员方法并调用.
        //1. 获取Student类的字节码文件对象.
        Class clazz = Class.forName("cn.itcast.demo3.Student");

        //2. 获取该类的构造器对象, 然后创建Student类的对象.
        Constructor con = clazz.getConstructor();
        Student stu = (Student)con.newInstance();
        //System.out.println(stu);

        //3. 获取该类的成员方法对象, 然后调用此方法.
        //3.1 调用公共的空参方法
        Method method1 = clazz.getMethod("show1");
        //打印方法对象
        System.out.println(method1);
        //打印方法名
        System.out.println(method1.getName());
        //调用此方法
        method1.invoke(stu);
        System.out.println("-------------------");

        //3.2 公共的带参方法
        Method method2 = clazz.getMethod("show2", int.class);
        //调用方法
        method2.invoke(stu,100);

        System.out.println("-------------------");

        //3.3 调用私有的带参方法
        Method method3 = clazz.getDeclaredMethod("show3", int.class, int.class);
        //开启暴力反射
        method3.setAccessible(true);
        //调用此方法
        int sum = (int)method3.invoke(stu,10,20);
        System.out.println("您录入的两个数的和是: " + sum);

        System.out.println("-------------------");

        //3.4 获取Student类中所有的成员方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
