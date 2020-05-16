package com.mark.csdn.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 原子性：AtomicInteger的简单使用演示
 * @Author: Mark
 * @CreateDate: 2020/3/25 15:31
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * 总结：对比非原子类的线程安全问题，使用了原子类之后，不需要加锁，也可以保证线程安全。
 *
 * @see java.util.concurrent.atomic.AtomicInteger
 */
@Slf4j
public class AtomicIntegerExample {

    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 5000;

    /**
     * count
     */
    private static  int count = 0;

    private static AtomicInteger atomicIntegerCount = new AtomicInteger(0);


    public static void main(String[] args) {
        //使用线程池中的10个线程执行5000次子任务
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            executorService.submit(()->{
                task();
            });
        }

        //关闭线程池
        executorService.shutdown();

        //打印结果
        log.info("普通变量 count的结果是："+count); //结果小于5000
        log.info("原子类变量 atomicIntegerCount的结果是："+atomicIntegerCount); //结果为5000
    }

    private static void task() {
        count++;
        atomicIntegerCount.getAndIncrement();
    }
}
