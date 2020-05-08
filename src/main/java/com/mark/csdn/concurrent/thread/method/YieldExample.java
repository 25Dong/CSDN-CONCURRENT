package com.mark.csdn.concurrent.thread.method;

/**
 * @Description: yield
 * @Author: Mark
 * @CreateDate: 2020/5/8 13:43
 * @Copyright : 豆浆油条个人非正式工作室
 * yield()应该做的是让当前 运行线程 回到 可运行状态，以允许具有相同优先级的其他线程获得运行机会。因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
 * 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果
 */
public class YieldExample {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + ":" + i);
                    if (i == 5) {
                        Thread.yield();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable, "ThreadA");
        Thread thread2 = new Thread(runnable, "threadA");
        thread1.start();
        thread2.start();
        //      yield() 执行非常不稳定，线程调度器不一定会采纳 yield() 出让 CPU 使用权的建议，从而导致了这样的结果
    }


}
