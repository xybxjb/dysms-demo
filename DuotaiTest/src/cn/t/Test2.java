package cn.t;

/**
 * @author 加鑫宇
 * @date 2020-10-24 12:21
 */
public class Test2 {
    public static void main(String[] args) {
//        byte[] bytes = new byte[10];
//        ByteBuffer buf = ByteBuffer.wrap(bytes);
//        System.out.println(buf);

        int a= 6555,b=30;
        System.out.println("----------");
        System.out.println(a);
        System.out.println(b);
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println(a);
        System.out.println(b);
    }
}
