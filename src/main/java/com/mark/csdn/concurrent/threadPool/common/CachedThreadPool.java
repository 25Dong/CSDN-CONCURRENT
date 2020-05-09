package com.mark.csdn.concurrent.threadPool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 具有缓存的线程池,适用于执行时间很短的大量任务
 * @Author: Mark
 * @CreateDate: 2020/4/5 16:53
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            executorService.execute(() -> log.info("task:{}",num));
        }
        //关闭线程池
        executorService.shutdown();
    }
}
