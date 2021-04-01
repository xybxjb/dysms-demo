package PackageTest01;

import java.net.SocketOption;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * LambdaTest表达式的使用
 * 共六种语法格式
 */
public class LambdaTest {

    public static void main(String[] args) {
        test5();
    }
    //语法格式一：无参，无返回值
    public static void test1(){
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                System.out.println("练习Lambda表达式的使用");
            }
        };
        r1.run();

        System.out.println("*************************");

        Runnable r2 = ()->System.out.println("练习Lambda表达式的使用2");
        r2.run();
    }

    //语法格式二：Lambda 需要一个参数，但是没有返回值
    public static void test2(){
        Consumer<String> con  = new Consumer<String>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么？");

        Consumer<String> con2  = (String s)->{
                System.out.println(s);
        };
        con.accept("一个是听的人当真了，一个是说的人当真了");
    }

    //语法格式三：数据类型可以省略，因为可由编辑器推断出，称为“类型推断”
    public static void test3(){
        Consumer<String> con1  = (String s)->{
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真了");

        System.out.println("**********************");

        Consumer<String> con2  = (String s)->{
            System.out.println(s);
        };
        con2.accept("一个是听的人当真了，一个是说的人当真了");
    }

    //语法格式四：Lambda 如只需要一个参数时，参数的小括号可以省略
    public static void test4(){
        Consumer<String> con1  = (s)->{
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真了");

        Consumer<String> con2  = s->{
            System.out.println(s);
        };
        con2.accept("一个是听的人当真了，一个是说的人当真了");
    }

    //语法格式五：Lambda 需要两个或者两个以上的参数，多条执行语句，并且可以有返回值
    public static void test5(){

        Comparator<Integer> com1 = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,6));

        System.out.println("*********************");

        Comparator<Integer> com2 = (o1, o2)-> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(23, 56));
    }

    //语法格式六：当Lambda体只有一条语句时，return与大括号可以一起省略（省略大括号时，return关键字也必须省略）
    public static void test6(){

        Comparator<Integer> com1 = (o1, o2)-> {
            return o1.compareTo(o2);
        };
        System.out.println(com1.compare(23, 56));

        Comparator<Integer> com2 = (o1, o2)-> o1.compareTo(o2);
        System.out.println(com2.compare(23, 56));

    }
}
