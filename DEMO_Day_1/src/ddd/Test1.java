package ddd;

import org.junit.Test;

/**
 * @author jiaxinyu
 * @date 2020-03-14 13:27
 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
 * 小兔子长到第四个月后每个月又生一对兔子，
 * 假如兔子都不死，问每个月的兔子总数为多少？  
 * 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765
 */
public class Test1 {
    @Test
    public static void main(String[] args) {
        int i = 0;
        //for循环代表的是月份
        for (i = 1; i <= 20; i++) {
            System.out.println(f(i));
        }
    }
    public static int f(int x)
    {    //第一个月和第二个月的兔子不变
        if(x==1 || x==2) {
            return 1;
        }else {
            //刚出生后3个月起生的和长到第四个月
            return f(x - 1) + f(x - 2);
        }
    }
}
    //   题目：判断101-200之间有多少个素数，并输出所有素数。
       class test2{
    //判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，  
    //则表明此数不是素数，反之是素数。  
           public static void main(String[] args) {
               for (int i = 101; i <=200; i++) {
                   if(i % 2!=0 ){
                       System.out.println(i);
                   }
               }
           }
}

class test3{
    /*题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，
    其各位数字立方和等于该数本身。例如：153是一个 "水仙花数 "，
    因为153=1的三次方＋5的三次方＋3的三次方。
    * */
    public static void main(String[] args) {
        for (int i = 100; i <= 999; i++) {
            int g,s,b;
            g = i/100;
            s = (i % 100)/10;
            b = i % 10;
            if(i== g*g*g+s*s*s+b*b*b){
                System.out.println(i);
            }
        }
    }
}
class test4{
    public static void main(String[] args) {

    }
}