package com.mark.csdn.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
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
