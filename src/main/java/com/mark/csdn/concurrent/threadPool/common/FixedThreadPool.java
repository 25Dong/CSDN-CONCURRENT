package com.mark.csdn.concurrent.threadPool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:Mark
 * date:2018/8/8  21:57
 * newFixedThreadPool：指定工作线程数量的线程池。
 * 如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
 * 它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
 * 但是，在线程池 空闲 时，即线程池中没有可运行任务时，
 * 它不会释放工作线程，所以会占用一定的系统资源。
 */
@Slf4j
public class FixedThreadPool {

    public static void main(String[] args) {
        //创建一个指定工作线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
                    log.info("task:{}",num);
                }
            });
        }
        //关闭线程池
        executorService.shutdown();
    }
}
