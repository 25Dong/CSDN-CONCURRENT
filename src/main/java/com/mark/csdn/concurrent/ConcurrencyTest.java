package com.mark.csdn.concurrent;


import com.mark.csdn.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 模拟高并发（基于线程池、CountDownLatch、Semaphore实现并发模拟），后续并发演示都是基础这个结构进行更改的
 * @Author: Mark
 * @CreateDate: 2019/6/29 12:14
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@NotThreadSafe
@Slf4j
public class ConcurrencyTest {

    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 5000;

    /**
     * 同时执行的线程数
     */
    private static final int THREAD_TOTAL = 200;

    /**
     * 总数：程序中每个线程对该变量进行加1操作
     */
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        //等待所有线程执行完
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
    }


}
