package com.mark.csdn.concurrent.aqs.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示ReentrantLock超过获取锁，该方式获取锁可以避免死锁
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:39
 * @Copyright : 豆浆油条个人非正式工作室
 * @see Lock#tryLock(long, java.util.concurrent.TimeUnit)
 */
public class ReentrantLockExample2 implements Runnable {
    private int flag;
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public ReentrantLockExample2(int flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        ReentrantLockExample2 tryLockDeadlock1 = new ReentrantLockExample2(1);
        ReentrantLockExample2 tryLockDeadlock2 = new ReentrantLockExample2(0);
        Thread thread1 = new Thread(tryLockDeadlock1);
        Thread thread2 = new Thread(tryLockDeadlock2);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 10; i < 100; i++) {
            if(flag == 1){
                //线程1
                try {
                    if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        //释放锁的最佳实践:获取到锁后 进行try-finally 释放锁
                        try{
                            System.out.println("--线程1获取到了锁1--");
                            Thread.sleep(new Random().nextInt(1000));

                            //尝试获取锁2
                            if(lock2.tryLock(800, TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("--线程1获取到锁2--");
                                    System.out.println("--线程1获取两把锁--");
                                    break;
                                }finally {
                                    System.out.println("--线程1释放到锁2--");
                                    lock2.unlock();
                                }
                            }else{
                                System.out.println("线程1获取锁2失败，已重试");
                            }

                        }finally {
                            lock1.unlock();
                            System.out.println("--线程1释放到锁1--");
                            //释放锁，睡眠片刻 让其他线程有更多的机会获取锁
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("--线程1获取锁1失败，已重试--");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(flag == 0){
                //线程2
                try {
                    if(lock2.tryLock(1000, TimeUnit.MILLISECONDS)){
                        //释放锁的最佳实践
                        try{
                            System.out.println("线程2获取到了锁2");
                            Thread.sleep(new Random().nextInt(900));

                            //尝试获取锁2
                            if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程2获取到锁1");
                                    System.out.println("线程2获取两把锁");
                                    break;
                                }finally {
                                    lock1.unlock();
                                    System.out.println("线程2是释放锁1");
                                }
                            }else{
                                System.out.println("线程2获取锁1失败，已重试");
                            }

                        }finally {
                            lock2.unlock();
                            System.out.println("线程2是释放锁2");
                            //释放锁，睡眠片刻 让其他线程有更多的机会获取锁
                            Thread.sleep(new Random().nextInt(1000));

                        }
                    }else{
                        System.out.println("线程2获取锁2失败，已重试");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

