package com.mark.csdn.concurrent.thread.method;

/**
 * @Description: join()方法的使用
 * @Author: Mark
 * @CreateDate: 2020/5/8 13:36
 * @Copyright : 豆浆油条个人非正式工作室
 * @see Thread#join()
 * @link https://kaiwu.lagou.com/course/courseInfo.htm?courseId=59#/detail/pc?id=1763
 *
 * 在一个线程中调用 otherThread.join() ，这时候当前线程会让出执行权给 other 线程，直到 other 线程执行完或者过了超时时间之后再继续执行当前线程
 */
public class JoinExample {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程睡眠"+i+"秒");
            }
        });

        thread.start();

        // 等待子线程先执行 
        thread.join();

        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程睡眠"+i+"秒");
        }
    }
}
