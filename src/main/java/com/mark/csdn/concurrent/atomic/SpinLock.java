package com.mark.csdn.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 自定义一个自旋锁
 * @Author: Mark
 * @CreateDate: 2020/3/14 22:09
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class SpinLock {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        //通过自旋方式获取锁（自旋即while循环）
        Thread currentThread = Thread.currentThread();
        int count = 0;
        while (!atomicReference.compareAndSet(null, currentThread)) {
            log.warn("获取锁失败，自旋获取");
            count++;
        }
        log.info("自旋的次数: "+count);
    }

    public void unLock(){
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread, null);
    }

    private static class Runner implements Runnable{
        private SpinLock spinLock = new SpinLock();
        @Override
        public void run() {
            log.info("尝试获取自旋锁");
            spinLock.lock();
            try{
                log.info("获取自旋锁成功");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unLock();
                log.info("释放自旋锁成功");
            }
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        new Thread(runner,"Thread1").start();
        new Thread(runner,"Thread2").start();
    }
}
