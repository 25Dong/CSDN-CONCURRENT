package com.mark.csdn.concurrent.visibility;
import com.mark.csdn.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: volatile-可见性,但是不能保证原子性。不能保证线程安全
 * @Author: Mark
 * @CreateDate: 2020/5/1 23:09
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * volatile的使用场景
 * 1.用作标识位
 * 2.double check（懒汉单例）
 */
@Slf4j
@NotThreadSafe
public class VolatileExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    /* volatile保证了可见性，但是不是线程安全的，不具有原子性*/
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
        // 1、count
        // 2、+1
        // 3、count
    }
}
