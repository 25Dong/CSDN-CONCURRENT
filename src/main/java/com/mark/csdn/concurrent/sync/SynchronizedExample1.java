package com.mark.csdn.concurrent.sync;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: synchronized修饰代码块和方法 ，作用都是调用的对象
 * @Author: Mark
 * @CreateDate: 2019/6/29 23:29
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample1 {

    /**
     * synchronized修饰代码块
     * @param objName 实例名称
     */
    public void lockBlock(String objName){
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("lockBlock() =>{}-{}",objName,i);
            }
        }
    }

    /**
     * synchronized修饰一个实例方法
     * @param objName 实例名称
     */
    public synchronized void lockMethod(String objName){
        for (int i = 0; i < 10; i++) {
            log.info("lockBlock() =>{}-{}",objName,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.lockMethod("example1");
        });
        executorService.execute(()->{
            example2.lockBlock("example2");
        });
        log.info("finish");
    }
}
