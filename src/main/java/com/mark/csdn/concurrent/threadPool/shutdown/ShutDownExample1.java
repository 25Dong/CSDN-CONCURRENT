package com.mark.csdn.concurrent.threadPool.shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 演示关闭线程池
 * @Author: Mark
 * @CreateDate: 2020/4/4 12:31
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ExecutorService#shutdown()
 */
public class ShutDownExample1 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Future<?> future = executorService.submit(new Task());
            System.out.println(future);
        }
        //shutdown方法并不是马上关闭线程池，知识告知线程池要关闭了，此时不会再接受提交过来的任务
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName()+" exec shutdown");

        //停止线程池后，再次提交任务，此时会抛出异常：RejectedExecutionException:
        executorService.execute(new Task());
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"正在执行！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
