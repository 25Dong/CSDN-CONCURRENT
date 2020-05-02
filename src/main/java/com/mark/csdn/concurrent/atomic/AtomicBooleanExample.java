package com.mark.csdn.concurrent.atomic;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: AtomicBoolean的演示
 * @Author: Mark
 * @CreateDate: 2019/6/29 15:51
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 应用场景：保证某段代码只是执行一次
 */
@ThreadSafe
@Slf4j
public class AtomicBooleanExample {

    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 5000;

    /**
     * 同时执行的线程数
     */
    private static final int THREAD_TOTAL = 200;

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

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
        log.info("count:{}",atomicBoolean);
    }

    private static void add() {
        // atomicBoolean 为false 的时候就会赋值为true
        if(atomicBoolean.compareAndSet(false,true)){
            //该代码判断只是执行一次：原子性保证了 更改操作执行一次
            log.info("execute");
        }
    }

}
