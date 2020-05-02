package com.mark.csdn.concurrent.immutable.finalkeywork;

/**
 * @Description: final修饰方法的注意点
 * @Author: Mark
 * @CreateDate: 2020/3/25 22:30
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：
 * 1.构造方法不能为final修饰；
 * 2.final修饰的方法不能被重写，和static方法一样（但是static可以声明一个一样的方法）
 */
public class FinalMethodDemo {

//    1.构造方法不能为final修饰；(Modifier 'final' not allowed here)
//    public final FinalMethodDemo(){
//
//    }

    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}

class SubClass extends FinalMethodDemo {

    //普通方法可以重写
    @Override
    public void drink() {
        super.drink();
        //final不能被子类重写,但是还是可以别子类调用的
        eat();
    }

//final修饰的方法不能被重写，
//    'eat()' cannot override 'eat()' in 'com.mark.learning.concurrent2.immutable.FinalMethodDemo'; overridden method is final
//    @Override
//    public void eat() {
//        super.eat();
//    }

//    静态方法也不能被重写
//    Method does not override method from its superclass
//    @Override
//    public void sleep() {
//        super.sleep();
//    }

//  但是static可以声明一个一样的方法
    public static void sleep() {

    }
}