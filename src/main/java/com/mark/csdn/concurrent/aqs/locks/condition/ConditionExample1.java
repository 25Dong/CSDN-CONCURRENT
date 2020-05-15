package com.mark.csdn.concurrent.aqs.locks.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示Condition的基本用法
 * @Author: Mark
 * @CreateDate: 2020/2/3 19:25
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class ConditionExample1 {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    private void await() {
        reentrantLock.lock();
        try {
            log.info("条件不足 开始await");
            //await（）方法之前需要调动lock（）获得同步监视器
            //使用当前线程进入WAITING状态
            condition.await();
            log.info("条件满足了，开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void signal() {
        reentrantLock.lock();
        try {
            log.info("准备工作完成，唤醒其他的线程");
            //signal（）方法之前需要调动lock（）获得同步监视器
            //通知waiting的线程继续执行
            condition.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //启动两个子线程，T1执行await（），T2执行signal（）唤醒T1
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConditionExample1 conditionDemo1 = new ConditionExample1();

        //T1进入waiting
        executorService.execute(conditionDemo1::await);

        //主线程睡眠，保证子线程已经awaiting，再执行下面的程序去唤醒
        Thread.sleep(1000);

        //启动T2唤醒T1
        executorService.execute(conditionDemo1::signal);

        //关闭线程池
        executorService.shutdown();

    }

}
