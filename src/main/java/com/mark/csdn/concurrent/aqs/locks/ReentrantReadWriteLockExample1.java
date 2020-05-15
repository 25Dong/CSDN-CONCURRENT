package com.mark.csdn.concurrent.aqs.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 模拟电影院买票的读写锁，读写锁的基本使用
 * @Author: Mark
 * @CreateDate: 2020/5/15 15:53
 * @Copyright : 豆浆油条个人非正式工作室
 * 注：
 * 1.读锁写锁不能同时别获取
 * 2.读锁可以被多线同时获取
 * 3.写锁只能被一个线程获取
 */
@Slf4j
public class ReentrantReadWriteLockExample1 {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read(){
        readLock.lock();
        try{
            log.info("获取读锁");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            log.info("释放读锁");
        }
    }

    private static void write(){
        writeLock.lock();
        try{
            log.info("获取写锁");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            log.info("释放写锁");
        }
    }

    public static void main(String[] args) {
        //声明两个读线程，两个写线程
        Thread thread1 = new Thread(ReentrantReadWriteLockExample1::read,"Thread1");
        Thread thread2 = new Thread(ReentrantReadWriteLockExample1::read,"Thread2");
        Thread thread3 = new Thread(ReentrantReadWriteLockExample1::write,"Thread3");
        Thread thread4 = new Thread(ReentrantReadWriteLockExample1::write,"Thread4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
