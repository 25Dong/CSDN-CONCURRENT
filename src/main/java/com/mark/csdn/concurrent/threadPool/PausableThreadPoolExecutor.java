package com.mark.csdn.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 自定义线程池
 * @Author: Mark
 * @CreateDate: 2020/4/6 10:35
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ThreadPoolExecutor
 */
@Slf4j
public class PausableThreadPoolExecutor extends ThreadPoolExecutor{

    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean isPaused;


    public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try{
            if(isPaused){
                t.setName(t.getName()+"被暂停了");
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void pause(){
        lock.lock();
        try {
            isPaused = true;
        }finally {
            lock.unlock();
        }
    }

    private void resume(){
        lock.lock();
        try {
            isPaused = false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PausableThreadPoolExecutor pauseableThreadPool = new PausableThreadPoolExecutor(10, 20, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            pauseableThreadPool.execute(()->{
                log.info("我被执行了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 在主线程中暂停所有的线程
        pauseableThreadPool.pause();

        //睡眠一会后 再次唤醒在线程池中睡眠的线程
        Thread.sleep(10000);
        pauseableThreadPool.resume();
        pauseableThreadPool.shutdown();
    }
}
