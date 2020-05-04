package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.ThreadSafe;

/**
 * @Description: 饿汉模式，单例实例在类装载时进行创建
 * @Author: Mark
 * @CreateDate: 2019/6/30 12:11
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6(){}

    //注意静态代码块和静态的变量的声明的顺序
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance() {
        return instance;
    }
}
