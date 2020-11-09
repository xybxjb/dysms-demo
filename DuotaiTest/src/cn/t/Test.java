package cn.t;

/**
 * @author 加鑫宇
 * @date 2020-10-19 9:52
 *
 *
 * 多态（英语：polymorphism）
 * 多态是面向对象的重要特性,简单点说:“一个接口，多种实现”，就是同一种事物表现出的多种形态。
 * 多态允许将子类的对象当作父类的对象使用，某父类型的引用指向其子类型的对象,
 * 调用的方法是该子类型的方法。这里引用和调用方法的代码编译前就已经决定了,而引用所指向的对象可以在运行期间动态绑定。
 *
 */
public class Test {
    public static void main(String[] args) {
        Animal an = new Dog();
        an.eat();
        an.show();
        Animal a2 = new Cat();
        a2.show();
        a2.eat();
        System.out.println("------------");
        if(an instanceof Dog){
            Dog d = (Dog) an;
            d.eat();
            d.LookDoor();

        }
    }

}
