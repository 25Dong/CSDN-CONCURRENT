package com.mark.csdn.concurrent.threadPool.shutdown;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: java.util.concurrent.ExecutorService#shutdownNow() 方法的演示
 * @Author: Mark
 * @CreateDate: 2020/4/4 12:46
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class ShutDownExample4 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
        //马上关闭线程池，正在执行的线程会被中断，在阻塞队列中的线程会被返回
        //马上关闭线程池，正在执行的线程会被中断，在阻塞队列中的线程会被返回
        List<Runnable> runnableList = executorService.shutdownNow();
        for(Runnable runnable : runnableList){
            runnable.run();
        }

    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                log.info("正在执行！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
