package com.mark.csdn.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @Description: 演示LongAccumulator的用法
 * @Author: Mark
 * @CreateDate: 2020/3/24 11:34
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 应用场景：
 * 大量的计算，通过多线程并发执行，前提条件是 每个任务的执行顺序没有强制要求
 */
public class LongAccumulatorExample {

    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 1);
        ExecutorService service = Executors.newFixedThreadPool(20);
        IntStream.range(1, 10).forEach(i -> service.submit(() -> longAccumulator.accumulate(i)));
        service.shutdown();
        while (!service.isTerminated()) {

        }
        System.out.println("result is  :" +longAccumulator.get());
    }
}
