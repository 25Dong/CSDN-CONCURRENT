package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.NotRecommend;
import com.mark.csdn.concurrent.annotations.ThreadSafe;

/**
 * @Description: 懒汉模式实现单例，单例实例在第一次使用时进行创建
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:59
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 构造函数私有化，不允许外部实例化
     */
    private SingletonExample3(){}

    /**
     * 单例实例
     */
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法:通过synchronized修饰静态方法，作用所有的对象线程安全
     * 但是因为对整个方法加上了锁，对性能多多少少都会有影响
     * @return 单例实例
     */
    public static synchronized SingletonExample3 getInstance(){
        if(null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
