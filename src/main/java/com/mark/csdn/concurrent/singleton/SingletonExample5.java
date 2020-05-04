package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.ThreadSafe;

/**
 * @Description: 懒汉模式实现单例，双重同步锁单例模式,单例实例在第一次使用时进行创建
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:59
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 *  因为指令重排序的存在，使得线程不安全，所有防止指令重排序的话
 *  可以加速volatile关键字修饰 {@code instance}
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 构造函数私有化，不允许外部实例化
     */
    private SingletonExample5() {
    }

    /**
     * 单例实例
     */
    private static volatile SingletonExample5 instance = null;


    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    /**
     * 静态的工厂方法:通过synchronized修饰静态方法，作用所有的对象线程安全
     * 但是因为对整个方法加上了锁，对性能多多少少都会有影响
     *
     * @return 单例实例
     */
    public static synchronized SingletonExample5 getInstance() {
        if (null == instance) { // 双重检测机制       // B
            synchronized (SingletonExample5.class) {  // 同步锁
                if (null == instance) {
                    instance = new SingletonExample5(); // A - 3
                }
            }
        }
        return instance;
    }

}
