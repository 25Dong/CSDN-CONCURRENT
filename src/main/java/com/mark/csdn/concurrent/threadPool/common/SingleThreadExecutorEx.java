package com.mark.csdn.concurrent.threadPool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 演示在newSingleThreadExecutor线程池中，当线程池中的工作线程发生异常或者停止时候会启动一个新的线程
 * @Author: Mark
 * @CreateDate: 2020/4/5 11:50
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class SingleThreadExecutorEx {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(-1));
        executorService.execute(new Task(4));
        executorService.shutdown();
    }

    private static class Task implements Runnable {

        private int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            if(id < 0){
                throw new IllegalArgumentException("参数不合法");
            }
            log.info("执行任务"+id);
        }
    }

}
