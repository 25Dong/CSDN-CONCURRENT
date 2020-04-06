package com.mark.csdn.concurrent.threadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Description: SingleThreadExecutor的简单使用
 * @Author: Mark
 * @CreateDate: 2020/4/5 11:49
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class SingleThreadExecutor {

    public static void main(String[] args) {
        //创建一个单线程化的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task:{}",num);
                }
            });
        }
        executorService.shutdown();//关闭线程池
    }
}
