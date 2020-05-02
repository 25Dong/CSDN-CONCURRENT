package com.mark.csdn.concurrent.sync;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: synchronized 锁住对象
 * @Author: Mark
 * @CreateDate: 2020/4/14 12:13
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample3 {

    private Object lockStr = new Object();

    public void  lockBlock1(){
        //synchronized作用于方法块，锁住的是括号里配置的对象
        synchronized (lockStr) {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("lockBlock()=>{}",i);
            }
        }
    }

    public void  lockBlock2(){
        //synchronized作用于方法块，锁住的是括号里配置的对象
        synchronized (lockStr) {
            for (int i = 0; i < 3; i++) {
                log.info("lockBlock2()=>{}",i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample3 example1 = new SynchronizedExample3();
        System.out.println(example1.lockStr.hashCode());

        SynchronizedExample3 example2 = new SynchronizedExample3();
        System.out.println(example2.lockStr.hashCode());

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.lockBlock1();
        });
        //executorService.execute(()->{
        //    example1.lockBlock2();
        //});
        //executorService.execute(()->{
        //    example2.lockBlock1();
        //});
        executorService.execute(()->{
            example2.lockBlock2();
        });
    }
}
