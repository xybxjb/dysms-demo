package cn.itcast.demo5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 *  Class类
 *     成员方法:
 *         public Field getField(String name)   返回一个Field对象，仅公共成员变量
 *         public Field getField(String name)   返回一个Field对象，可获取私有成员变量
 *         public Field[] getDeclaredFields()   返回此类所有（含私有）成员变量的数组
 *
 *  Field: 属性类, 用来表示所有的域(属性, 成员变量)对象的.
 *     概述: 属于java.base模块下的 java.lang.reflect包下的类.
 *
 *     成员方法:
 *        public void setAccessible(boolean flag);           是否开启暴力反射(true: 开启)
 *        public void set(Object obj, Object value);         设置obj对象的指定属性值为value
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws Exception {
        //需求: 通过反射获取成员变量并使用.
        //1. 获取Student类的字节码文件对象.
        Class clazz = Class.forName("cn.itcast.demo5.Student");

        //2. 通过字节码文件对象获取构造器对象, 然后创建学生类对象.
        Constructor con = clazz.getConstructor();
        Student stu = (Student)con.newInstance();
        //Student stu2 = (Student)clazz.getConstructor().newInstance();   链式编程

        //3. 设置学生对象的各个属性值.
        //3.1 设置姓名
        Field field1 = clazz.getField("name");
        field1.set(stu, "张无忌");

        //3.2 设置年龄
        Field field2 = clazz.getDeclaredField("age");
        //开启暴力反射
        field2.setAccessible(true);
        field2.set(stu, 30);

        //4. 打印学生对象.
        System.out.println(stu);
    }
}
