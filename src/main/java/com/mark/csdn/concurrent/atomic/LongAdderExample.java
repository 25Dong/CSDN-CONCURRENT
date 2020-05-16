package com.mark.csdn.concurrent.atomic;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: 演示高并发场景下，LongAdder比AtomicLong性能好
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:10
 * @Copyright : 豆浆油条个人非正式工作室
 * @see AtomicLongerExample2
 */
@ThreadSafe
@Slf4j
public class LongAdderExample {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
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
        log.info("运行结果：{}",counter.sum());
        log.info("LongAdder 耗时时间是："+(endTime-startTime));

    }

    private static class Task implements Runnable {

        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }

}
