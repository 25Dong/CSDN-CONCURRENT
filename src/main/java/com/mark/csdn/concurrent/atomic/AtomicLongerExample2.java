package com.mark.csdn.concurrent.atomic;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: 演示高并发场景下，LongAdder比AtomicLong性能好
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:10
 * @Copyright : 豆浆油条个人非正式工作室
 * @see LongAdderExample
 * 差异：
 * AtomicLong：每一次加法都需要做同步操作，所以在高并发场景下，冲突比较多，也就降低了效率
 * LongAdder：每一个线程都有自己的计数器，用来计算在自己线程内计数。最后再汇总求和
 */
@ThreadSafe
@Slf4j
public class AtomicLongerExample2 {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong();
        ExecutorService service =  Executors.newFixedThreadPool(20);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();

        //线程没有终止的话，让他一直死循环
        while (!service.isTerminated()) {

        }
        long endTime = System.currentTimeMillis();
        log.info("运行结果：{}",counter);
        log.info("AtomicLong 耗时时间是："+(endTime-startTime));

    }

    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }

}
