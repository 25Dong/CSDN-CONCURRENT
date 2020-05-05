package com.mark.csdn.concurrent.commonunsafe.list;


import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: CopyOnWriteArrayList线程安全，适合读多写少的场景
 * @Author: Mark
 * @CreateDate: 2020/5/1 23:59
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 5000;

    /**
     * 同时执行的线程数
     */
    private static final int THREAD_TOTAL = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(finalI);
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
        log.info("list size:{}",list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
