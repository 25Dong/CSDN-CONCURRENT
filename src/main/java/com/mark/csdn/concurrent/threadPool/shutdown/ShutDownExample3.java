package com.mark.csdn.concurrent.threadPool.shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: awaitTermination
 * @Author: Mark
 * @CreateDate: 2020/4/4 12:46
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
 */
public class ShutDownExample3 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        //shutdown方法并不是马上关闭线程池，知识告知线程池要关闭了，此时不会再接受提交过来的任务
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName()+" exec shutdown");

        boolean awaitTermination = executorService.awaitTermination(7L, TimeUnit.SECONDS);
        System.out.println("awaitTermination "+awaitTermination);

    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"正在执行！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
