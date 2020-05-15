package com.mark.csdn.concurrent.aqs.flowcontrol.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 多等一和一等多的结合使用
 * @Author: Mark
 * @CreateDate: 2020/2/2 23:29
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class CountDownLatchExample3 {

    private final static int THREAD_COUNT = 5;
    //开始所有运动员等待 裁判的一次开始枪声的闭锁
    private final static CountDownLatch BEGIN_CNT = new CountDownLatch(1);
    //所有运动员跑步结束的闭锁
    private final static CountDownLatch END_CNT = new CountDownLatch(THREAD_COUNT);

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {

        //模拟五个选手跑步
        for (int i = 0; i < THREAD_COUNT; i++) {
            int finalI = i+1;
            executorService.submit(() -> {
                try {
                    log.info("选手" + finalI + "准备好了，等待裁判发令！！");
                    //多等一：等待裁判的枪声
                    BEGIN_CNT.await();
                    log.info("选手" + finalI + "开始跑步");
                    Thread.sleep((long) (Math.random()*1000));
                    log.info("选手" + finalI + "跑到终点");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //结束的闭锁减1
                    END_CNT.countDown();
                }
            });
        }

        //裁判员检查发令枪...
        Thread.sleep(5000);
        BEGIN_CNT.countDown();

        //一等多：等待每个运动员都到达终点
        END_CNT.await();
        log.info("所有人到达终点 比赛结束！");

        executorService.shutdown();
    }

}
