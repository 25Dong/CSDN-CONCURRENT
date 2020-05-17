package com.mark.csdn.concurrent.threadPool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * workStealingPool 创建持有足够的线程池来达到快速运算的目的，在内部通常使用多个队列来减少调度产生的竞争问题
 * author:Mark
 * date:2020/05/17  21:57
 *
 * @see ForkJoinPool
 * @since 1.8
 */
@Slf4j
public class WorkStealingPool {

    public static void main(String[] args) {
        //创建一个指定工作线程数量的线程池
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task:{}", num);
                }
            });
        }
        //关闭线程池
        executorService.shutdown();
    }
}
