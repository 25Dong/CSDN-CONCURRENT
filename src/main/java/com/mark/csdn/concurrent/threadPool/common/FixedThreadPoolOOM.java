package com.mark.csdn.concurrent.threadPool.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:  示newFixedThreadPool出错的情况(指定vm参数为-Xms8m -Xmx8m) 最大堆和最下堆我这里指定为8M
 * @Author: Mark
 * @CreateDate: 2020/4/5 11:36
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class FixedThreadPoolOOM {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //预期：只有一个线处活动线程，不断提交给线程的任务会进入LinkedBlockingQueue无界队列中，最后可能出现OOM异常
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在执行任务！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
