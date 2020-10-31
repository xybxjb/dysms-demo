package cn.itcast.demo4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectDemo1 {
    public static void main(String[] args) throws Exception {
        //需求: 通过反射获取类的setter方法，使用该方法为属性赋值
        //1. 通过反射获取Student类的字节码文件对象.
        Class clazz = Class.forName("cn.itcast.demo4.Student");

        //2. 通过反射获取Student类的构造方法, 并创建该类的对象.
        Constructor con = clazz.getConstructor();
        Student stu = (Student)con.newInstance();

        //3. 获取到指定的setName()方法, 给Student对象设置值.
        Method method1 = clazz.getMethod("setName", String.class);
        //调用此方法
        method1.invoke(stu,"张无忌");

        //4. 打印学生对象.
        System.out.println(stu);
    }
}
