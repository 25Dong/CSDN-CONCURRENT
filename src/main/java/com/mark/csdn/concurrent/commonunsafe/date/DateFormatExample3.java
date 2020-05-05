package com.mark.csdn.concurrent.commonunsafe.date;


import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * @Description: DateTimeFormatter 线程安全
 * @Author: Mark
 * @CreateDate: 2019/6/29 12:14
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 *        <!-- 线程安全时间格式化 -->
 *         <dependency>
 *             <groupId>joda-time</groupId>
 *             <artifactId>joda-time</artifactId>
 *             <version>2.9</version>
 *         </dependency>
 */
@ThreadSafe
@Slf4j
public class DateFormatExample3 {

    /**
     * 请求总数
     */
    private static final int CLIENT_TOTAL = 5000;

    /**
     * 同时执行的线程数
     */
    private static final int THREAD_TOTAL = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(finalI);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        //等待所有线程执行完
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
    }

    private static void update(int i) {
        //观察每次的打印结果结构一致
        log.info("{}, {}", i, DateTime.parse("20180208", dateTimeFormatter).toDate());
    }


}
