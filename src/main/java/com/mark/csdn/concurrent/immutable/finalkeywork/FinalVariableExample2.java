package com.mark.csdn.concurrent.immutable.finalkeywork;

/**
 * @Description: final修饰全局静态变量的赋值时机
 * @Author: Mark
 * @CreateDate: 2020/3/25 22:18
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：
 * 1.在声明变量的等号右边直接赋值；
 * 2.在static代码中赋值，但是不能在普通代码块汇总赋值
 */
public class FinalVariableExample2 {

//1.在声明变量的等号右边直接赋值；
//    private static final int a = 1

//2.在static代码中赋值，但是不能在普通代码块汇总赋值
    private static final int a;

    static {
        a = 1;
    }
}
