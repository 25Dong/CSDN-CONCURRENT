package com.mark.csdn.concurrent.immutable.finalkeywork;

/**
 * @Description: final修饰全局变量的赋值时机
 * @Author: Mark
 * @CreateDate: 2020/3/25 22:13
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * <p>
 * 总结：
 * 1.在声明变量的等号右边直接赋值；
 * 2.在构造函数中赋值；
 * 3.在类的初始代码块中赋值（不常用）
 */
public class FinalVariableExample1 {

//1.在声明变量的等号右边直接赋值；
//  private  final int a = 1;

//2.在构造函数中赋值；
//    private final int a;
//
//    public FinalVariableDemo1(int a) {
//        this.a = a;
//    }

    //3.在类的初始代码块中赋值（不常用）
    private  final int a;

    {
        a = 1;
    }
}
