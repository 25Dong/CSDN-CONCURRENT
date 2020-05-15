package com.mark.csdn.concurrent.aqs.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 演示ReentrantReadWriteLock可以降级，不能升级
 * @Author: Mark
 * @CreateDate: 2020/5/15 15:56
 * @Copyright : 豆浆油条个人非正式工作室
 * 锁降级：如果线程持有写锁，如果可以在不释放写锁的情况下，获取读锁，这就是锁降级。ReadWriteLock 是支持锁降级的。
 * 锁升级：如果线程持有读锁，那么他是否可以不释放读锁，直接获取写锁。这意味着从一个低级别的锁升级到高级别的锁。其实就是变相的插队，无视其它在排队等待写锁的线程。ReadWriteLock 并不支持锁升级。
 */
@Slf4j
public class ReentrantReadWriteLockExample2 {

    /**
     * 读写锁，对比 公平和不公平读写锁的执行效果
     */
//    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 读锁升级
     */
    public static void readUpgrading() {
        readLock.lock();
        try {
            log.info(" ====>成功获取 读锁<=====");
            Thread.sleep(1000);
            log.warn("读锁升级 会带来阻塞");
            writeLock.lock();
            log.info(" ====>成功获取 写锁<=====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info( "====******** 成功释放 写锁 *********=========");
            writeLock.unlock();
            log.info( "====******** 成功释放 读锁 *********=========");
            readLock.unlock();
        }
    }

    /**
     * 写锁降级
     */
    public static void writeDowngrading() {
        writeLock.lock();
        try {
            log.info("=============================================>>>成功获取 写锁");
            Thread.sleep(1000);
            readLock.lock();
            log.info("=============================================>>>成功获取 读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            log.info("成功释放 写读锁");
            writeLock.unlock();
            log.info("成功释放 写读锁");
        }
    }

    public static void main(String[] args) {
//        Thread thread1 = new Thread(Upgrading::writeDowngrading,"Thread1");
//        thread1.start();

        new Thread(ReentrantReadWriteLockExample2::readUpgrading).start();

    }
}
