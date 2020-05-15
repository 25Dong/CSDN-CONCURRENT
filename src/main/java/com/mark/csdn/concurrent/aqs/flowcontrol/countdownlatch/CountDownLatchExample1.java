package com.mark.csdn.concurrent.aqs.flowcontrol.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 一等多：工厂中，质检，5个工人检查，所有人都认为通过，才通过
 * @Author: Mark
 * @CreateDate: 2020/2/2 21:40
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class CountDownLatchExample1 {


    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {

        //启动五个工人执行的线程
        for (int i = 0; i < 5; i++) {
            int finalI = i+1;
            executorService.execute(()->{
                try {
                    log.info("工人"+ finalI +"开始检查工作！");
                    Thread.sleep((long) (Math.random()*10000));
                    log.info("工人"+ finalI +"完成检查工作！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //确保减一操作的执行，
                    countDownLatch.countDown();
                }
            });
        }

        log.warn("等待五个工人检查完。。。。");
        countDownLatch.await();
        log.info("五个工人检查完成。。。。执行工作");
        executorService.shutdown();
    }
}
