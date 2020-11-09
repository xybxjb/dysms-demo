package cn.t;

/**
 * @author 加鑫宇
 * @date 2020-10-19 9:51
 */
public class Cat extends Animal {
   @Override
    public void eat(){
        System.out.println("猫吃鱼");
    }
    @Override
    public void show(){
        System.out.println("猫打鼾");
    }
}
