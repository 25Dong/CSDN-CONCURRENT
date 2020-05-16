package com.mark.csdn.concurrent.aqs.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLock可以重入锁：获取重入的次数
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:42
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ReentrantLock#getHoldCount()
 */
@Slf4j
public class ReentrantLockExample3 {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            log.info("已经对资源进行了处理");
            if (lock.getHoldCount()<5) {
                log.info("锁获得了"+lock.getHoldCount()+" 次");
                accessResource();
                log.info("锁获得了"+lock.getHoldCount()+" 次");
            }
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        accessResource();
    }
}
