/**
 * @Description: 通过线程安全的单例实现安全发布对象
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:58
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 单例实现的方法：
 *  方式一：饿汉模式：
 *  特点线程安全，因为在类加载的时候已经完成了初始化。如果该实例没有在程序中调用，那么会操作资源的浪费
 * {@link com.mark.learning.concurrent.singleton.SingletonExample2,com.mark.learning.concurrent.singleton.SingletonExample6}
 *
 *  方式二：懒汉模式：单例实例在第一次使用时进行创建
 *  1.普通写法线程不安全 {@link com.mark.learning.concurrent.singleton.SingletonExample1}
 *
 *  2.synchronized修饰获取实例的静态方法是线程安全的，但是不推荐使用
 * {@link com.mark.learning.concurrent.singleton.SingletonExample3}
 *
 * 3.double check 但是存在指令排序 导致线程不安全
 * {@link com.mark.learning.concurrent.singleton.SingletonExample4}
 *
 * 4.double check + volatile 线程安全
 * {@link com.mark.learning.concurrent.singleton.SingletonExample5}
 *
 *
 * 方式三：通过枚举获取实例
 * 线程安全、推荐使用
 * {@link com.mark.learning.concurrent.singleton.SingletonExample7}
 */
package com.mark.csdn.concurrent.singleton;