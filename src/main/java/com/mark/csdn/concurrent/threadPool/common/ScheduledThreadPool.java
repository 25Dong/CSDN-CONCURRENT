package com.mark.csdn.concurrent.threadPool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 具有定时执行任务功能的线程池
 * @Author: Mark
 * @CreateDate: 2020/5/9 11:08
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class ScheduledThreadPool {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);


    public static void main(String[] args) {
        log.info("start");
        for (int i = 0; i < 1000; i++) {
            //用法一：设置延迟多少秒后在执行任务
            //executorService.schedule(new Task(), 5, TimeUnit.SECONDS);

            //用法二：延迟1秒后启动线程执行任务，然后每隔3秒执行一次任务，需要注意的是不能关闭线程池，否则定时任务就不能如期运行了
            executorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
        }
        //executorService.shutdown();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("executor thread is " + Thread.currentThread().getName());
        }
    }
}
