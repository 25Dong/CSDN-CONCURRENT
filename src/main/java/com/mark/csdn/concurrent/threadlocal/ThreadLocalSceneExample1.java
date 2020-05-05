package com.mark.csdn.concurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: ThreadLocal的使用场景1：每个线程需要独享一个工具类对象（思考，很多时候声明为局部变量会不会更好）
 * @Author: Mark
 * @CreateDate: 2020/5/5 13:49
 * @Copyright : 豆浆油条个人非正式工作室
 * @see SimpleDateFormat 本身是线程安全的
 */
@Slf4j
public class ThreadLocalSceneExample1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                SimpleDateFormat simpleDateFormat = ThreadLocalDateFormat.simpleDateFormatThreadLocal.get();

                String format = simpleDateFormat.format(new Date());
                log.info("time is " + format);
            });
        }
        executorService.shutdown();
    }

    private static class ThreadLocalDateFormat{

        /**
         * 在定义的时候已经定义初始值了，当线程地址get的才会触发该方法
         */
        private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
