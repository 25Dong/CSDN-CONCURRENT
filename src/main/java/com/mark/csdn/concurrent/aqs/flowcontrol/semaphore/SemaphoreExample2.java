package com.mark.csdn.concurrent.aqs.flowcontrol.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 一个线程获取多个许可
 * @Author: Mark
 * @CreateDate: 2020/2/3 14:43
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：声明的许可大小为3，而每个线程申请获取许可的大小为3，即同时访问某一个资源（task方法）最多只能有1个线程；
 * 运行结果分析：log 单个线程 分别依次打印
 */
@Slf4j
public class SemaphoreExample2 {

    private static final int THREAD_COUNT = 20;
    private static  Semaphore semaphore = new Semaphore(3);
    private static  ExecutorService executorservice = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i+1;
            executorservice.execute(()->{
                try {
                    //获取一个许可
                    semaphore.acquire(3);

                    task(threadNum);

                    //释放一个许可
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorservice.shutdown();
    }

    private static void task(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
