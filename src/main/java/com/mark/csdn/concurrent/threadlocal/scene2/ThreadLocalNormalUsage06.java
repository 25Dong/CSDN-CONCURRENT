package com.mark.csdn.concurrent.threadlocal.scene2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 场景二：每个线程内需要保存全局变量
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:39
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class ThreadLocalNormalUsage06 {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        //主线程
        new UserFilter().process();

        //将1000条任务交给10个线程执行
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                new UserFilter().process();
            });
        }

        //关闭线程池
        executorService.shutdown();

    }
}
