package com.mark.csdn.concurrent.aqs.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示ReentrantLock获取锁的时候可能被中断
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:36
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ReentrantLock#lockInterruptibly()
 */
@Slf4j
public class ReentrantLockExample1 implements Runnable{
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try{
                log.info(Thread.currentThread().getName() + "获取了锁");

                //这里让当前的睡眠以后，是为了模拟当前线程不会那么快释放锁
                //这样另外的线程过来的时候就不会顺利的获取到锁
                Thread.sleep(5000);

            }catch (InterruptedException e){
                log.info(Thread.currentThread().getName() +"=======睡眠期间被中断！");
            }finally {
                lock.unlock();
                log.info(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(Thread.currentThread().getName() + "=======获取锁期间被中段！");
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample1 interruptibly = new ReentrantLockExample1();
        Thread threadA = new Thread(interruptibly,"Thread-A");
        Thread threadB = new Thread(interruptibly,"Thread-B");
        threadA.start();
        threadB.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * 打断：在打断时候 这里需要分两种情况
         * 1.如果此时 线程B已经是已经获得了锁的，此时去打断它，代码会进入sleep的try catch中 ==》“=======睡眠期间被中断！”
         * 2.如果此时 线程B还没获锁（锁已经被A拿走了），此时去打断它，代码会进入 lock.lockInterruptibly() catch中 ==》“==============获取锁期间被中段！”
         */
        threadB.interrupt();
    }

}
