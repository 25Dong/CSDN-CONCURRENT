package com.mark.csdn.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description: AtomicReferenceFieldUpdater的简单使用演示：对普通变量进行升级
 * @Author: Mark
 * @CreateDate: 2020/3/25 16:37
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 使用场景：偶尔 需要进行原子类操作
 * 缺点：可见范围（声明变量时需要用volatile进行修饰）， 不支持static修饰的变量
 */
public class AtomicReferenceFieldUpdaterExample implements Runnable{


    public static class Candidate{
        volatile  int score;
    }

    private static Candidate tom;
    private static Candidate peter;
    private static AtomicIntegerFieldUpdater<Candidate> scoreUpdate =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            tom.score++;
            scoreUpdate.incrementAndGet(peter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        Runnable runner = new AtomicReferenceFieldUpdaterExample();
        Thread thread1 = new Thread(runner);
        Thread thread2 = new Thread(runner);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("普通变量：" + tom.score);
        System.out.println("升级变量："+peter.score);
    }
}
