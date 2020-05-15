package com.mark.csdn.concurrent.aqs.flowcontrol.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description: 循环栅栏 指定等待时间
 * @Author: Mark
 * @CreateDate: 2020/2/4 14:48
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        //启动10个线程
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            final int threadNum = i + 1;
            executorService.execute(()->{
                try {
                    task(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    /**
     * 子线程执行的方法
     */
    private static void task(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{} is 准备好了",threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        log.info("{} is 完成工作",threadNum);
    }
}
