/**
 * @Description: 目录
 * @Author: Mark
 * @CreateDate: 2020/5/2 11:13
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 第一章
 *  1-1 环境的搭建
 *  1-2 自定义注解 {@link com.mark.csdn.concurrent.annotations}
 *  1-3 并发模式代码 {@link com.mark.csdn.concurrent.ConcurrencyTest}
 *
 * 第二章 线程安全性
 *  2-1 原子性（synchronized、原子类、lock可以保证原子性）
 *    2-1-1 synchronized {@link com.mark.csdn.concurrent.sync}
 *    2-1-2 原子类 {@link com.mark.csdn.concurrent.atomic}
 *    2-1-3 lock {@link com.mark.csdn.concurrent.lock}
 *
 *  2-2 可见性（synchronized、volatile、final 这三个关键字保证了可见性）
 *    2-2-1 volatile {@link com.mark.csdn.concurrent.visibility.VolatileExample1}
 *    2-2-2 final {@link com.mark.csdn.concurrent.immutable.finalkeywork}
 *
 *  2-3 有序性（synchronized、volatile保证了有序性）
 *
 * 第三章 安全发布对象
 *  3-1 安全发布对象与对象逸出{@link com.mark.csdn.concurrent.publish}
 *  3-2 通过单例实现安全发布对象{@link com.mark.csdn.concurrent.singleton}
 *
 * 第四章 线程安全策略
 *  4-1 不可变对象 {@link com.mark.csdn.concurrent.immutable}
 *  4-2 线程封闭 {@link com.mark.csdn.concurrent.threadlocal}
 *  4-3 常见线程不安全的类与对应线程安全的类 {@link com.mark.csdn.concurrent.commonunsafe}
 *
 *  第五章 线程基础
 *  5-1 死锁 {@link com.mark.csdn.concurrent.deadLock}
 *  5-2 Thread类中常用的方法 {@link com.mark.csdn.concurrent.thread}
 *
 *  第六章 JUC
 *  6-1 线程池 {@link com.mark.csdn.concurrent.threadPool}
 *  6-2 Future {@link com.mark.csdn.concurrent.future}
 *  6-3 原子类 {@link com.mark.csdn.concurrent.atomic}
 *  6-4 locks {@link com.mark.csdn.concurrent.aqs.locks,com.mark.csdn.concurrent.lock}
 */
package com.mark.csdn.concurrent;