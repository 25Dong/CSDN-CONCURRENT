package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.NotThreadSafe;

/**
 * @Description: 懒汉模式实现单例，单例实例在第一次使用时进行创建
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:59
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 构造函数私有化，不允许外部实例化
     */
    private SingletonExample1(){}

    /**
     * 单例实例
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return 单例实例
     */
    public static SingletonExample1 getInstance(){
        if(null == instance){
            //多线程的环境中该代码可能会被重复执行
            instance = new SingletonExample1();
        }
        return instance;
    }

}
