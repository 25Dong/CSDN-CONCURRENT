package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.ThreadSafe;

/**
 * @Description: 饿汉模式实现单例，单例实例在类装载时进行创建
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:59
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 构造函数私有化，不允许外部实例化
     */
    private SingletonExample2(){}

    /**
     * 单例实例
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 静态的工厂方法
     * @return 单例实例
     */
    public static SingletonExample2 getInstance(){
        return instance;
    }

}
