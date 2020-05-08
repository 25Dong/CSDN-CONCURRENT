package com.mark.csdn.concurrent.thread.method;

/**
 * @Description:
 * @Author: Mark
 * @CreateDate: 2020/5/8 13:53
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 1.调用 interrupt 方法，并不会影响可中断方法之外的逻辑。线程不会中断，会继续执行。这里的中断概念并不是指中断线程；
 * 2.一旦调用了 interrupt 方法，那么线程的 interrupted 状态会一直为 ture（没有通过调用可中断方法或者其他方式主动清除标识的情况下）；
 * 总结：
 * 通过上面实现我们了解了 interrupt 方法中断的不是线程。它中断的其实是可中断方法，如 sleep 。可中断方法被中断后，会把 interrupted 状态归位，改回 false 。
 */
public class InterruptedExample2 {

    public static void main(String[] args) throws InterruptedException {
        Thread xiaopang = new Thread(()->{
            for(int i=0; i<100 ;i++){
                System.out.println("I'm doing my work");
                try {
                    System.out.println("I will sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("My sleeping was interrupted");
                }
                System.out.println("I'm interrupted?"+Thread.currentThread().isInterrupted());
            }
        });
        xiaopang.start();
        Thread.sleep(1);
        xiaopang.interrupt();
    }
}
