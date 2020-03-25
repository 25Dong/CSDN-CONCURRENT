package com.mark.csdn.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description: AtomicArray的简单演示
 * @Author: Mark
 * @CreateDate: 2020/3/25 16:05
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class AtomicIntegerArrayExample {

    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 100;

    /**
     * 声明整型的原子数组，数组长度为20
     */
    private static AtomicIntegerArray array = new AtomicIntegerArray(20);


    public static void main(String[] args) {
        //使用线程池中的10个线程执行100次子任务
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            executorService.submit(()->{
                task();
            });
        }

        //关闭线程池
        executorService.shutdown();

        //这里的循环是为了让线程池中的线程执行完毕
        while (!executorService.isTerminated()) {

        }

        //预期结果，数组中每个元素的值都为100
        for (int i = 0; i < array.length(); i++) {
            if(array.get(i) != CLIENT_TOTAL){
                log.error("发现错误的元素，下标为"+i);
            }
        }
        log.info("运行结束！");
    }

    /**
     * 子任务：每一个线程都对原子数组中的每一个元素都进行加一操作
     */
    private static void task() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}
