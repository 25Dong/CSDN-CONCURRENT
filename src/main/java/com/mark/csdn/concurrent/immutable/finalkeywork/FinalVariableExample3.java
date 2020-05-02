package com.mark.csdn.concurrent.immutable.finalkeywork;

/**
 * @Description: final修饰局部变量的赋值时机
 * @Author: Mark
 * @CreateDate: 2020/3/25 22:20
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：
 * 1.在声明变量的等号右边直接赋值；
 * 2.final修饰局部变量的时候，不规定赋值时机，只是要求在使用前必须赋值；
 *   这和方法中的非final方法的要求也是一样的。
 */
public class FinalVariableExample3 {

    public void test(){
//        final int a = 1;

        final int a;
        a = 1;
        System.out.println(a);
    }
}
