package cn.t;

/**
 * @author 加鑫宇
 * @DATE 2021/3/19 14:18
 */

public class Test3 {

    public static void main(String [] args){
        int i=1;
        try {
            i++;
            System.out.println(i);
            throw new Exception();
        }catch (Exception e) {
            i++;
            System.out.println(i);
            return;
        }finally {
            i =10;
            System.out.println();
        }

    }
}
