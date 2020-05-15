package com.mark.csdn.concurrent.aqs.flowcontrol.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 尝试获取许可
 * @Author: Mark
 * @CreateDate: 2020/2/3 14:43
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：声明的许可大小为3，即同时访问某一个资源（task方法）最多只能有3个线程；
 * 运行结果分析：只会打印3个线程的运行结果，因为程序中当有3个线程许可运行时，其他线程过来，尝试获取许可，这时已经
 * 没有足够的许可了，申请许可的线程就会继续往下执行，放弃获取许可。
 * @see Semaphore#tryAcquire()
 */
@Slf4j
public class SemaphoreExample3 {

    private static final int THREAD_COUNT = 20;
    private static  Semaphore semaphore = new Semaphore(3);
    private static  ExecutorService executorservice = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i+1;
            executorservice.execute(()->{
                try {
                    if(semaphore.tryAcquire()){

                        task(threadNum);

                        //释放一个许可
                        semaphore.release();
                    }

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
