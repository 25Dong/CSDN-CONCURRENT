package com.mark.csdn.concurrent.aqs.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLock公平性
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:43
 * @Copyright : 豆浆油条个人非正式工作室
 * @see  java.util.concurrent.locks.ReentrantLock
 * 公平锁：
 * 打印的顺次就是：线程0到9依次 第一次获取到锁 ，然后又依次第二次获取到锁
 *
 * 非公平锁：
 * 打印的顺序是：可能线程1获取第一次获取到了，马上紧接着有第二次获取了锁，因为非公锁允许线程存在插队的行为；
 * 线程1执行第一次打印后。以为他还是很活跃的，就有很大的概率第二次获取到。这样充分利用和节省了线程的唤醒的时间
 */
@Slf4j
public class ReentrantLockExample4 {
    //    private Lock queueLock = new ReentrantLock(false);
    private Lock queueLock = new ReentrantLock(true);

    /**
     * 打印
     *
     * @param document 打印文档
     */
    public void printJob(Object document) {
        //模拟打印第一份材料
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10) + 1;
            log.info( "获取锁一次 正在打印1，需要" + duration);
            Thread.sleep(duration * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            queueLock.unlock();
        }

        //模拟打印重复打印
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10) + 1;
            log.info( "获取到锁两次 正在打印2，需要" + duration);
            Thread.sleep(duration * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }

    @Slf4j
    static class  Job implements Runnable {

        ReentrantLockExample4 printQueue;

        public Job(ReentrantLockExample4 printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {
            log.info("开始打印");
            printQueue.printJob(new Object());
            log.info("====================打印完毕");
        }
    }

    public static class FairLock {

        public static void main(String[] args) {
            ReentrantLockExample4 printQueue = new ReentrantLockExample4();

            //开启十个线程
            Thread[] threads = new Thread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(new Job(printQueue));
            }

            //有顺序启动十个线程
            for (int i = 0; i < 10; i++) {
                threads[i].start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}






