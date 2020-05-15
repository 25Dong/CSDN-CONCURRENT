package com.mark.csdn.concurrent.aqs.flowcontrol.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 多等一：模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开始跑步。
 * @Author: Mark
 * @CreateDate: 2020/2/2 23:29
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class CountDownLatchExample2 {

    private final static int THREAD_COUNT = 5;
    private static CountDownLatch BEGIN_CNT = new CountDownLatch(1);
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {

        //模拟五个选手跑步
        for (int i = 0; i < THREAD_COUNT; i++) {
            int finalI = i+1;
            executorService.execute(() -> {
                try {
                    log.info("选手" + finalI + "准备好了，等待裁判发令！！");
                    BEGIN_CNT.await();
                    log.info("选手" + finalI + "开始跑步");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //裁判员检查发令枪...
        Thread.sleep(5000);
        BEGIN_CNT.countDown();
        executorService.shutdown();
    }

}
