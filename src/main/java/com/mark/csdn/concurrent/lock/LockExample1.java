package com.mark.csdn.concurrent.lock;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: synchronized锁，基于JVM实现，JVM帮助我们自动加锁和解锁不会出现死锁情况；适用于并发不高的情况
 * @Author: Mark
 * @CreateDate: 2019/6/29 12:14
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@ThreadSafe
@Slf4j
public class LockExample1 {

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

    private static  synchronized void add() {
        count++;
    }


}
