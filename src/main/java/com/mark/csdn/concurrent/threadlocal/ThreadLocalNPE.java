package com.mark.csdn.concurrent.threadlocal;

/**
 * @Description: ThreadLocal的空指针异常
 * @Author: Mark
 * @CreateDate: 2020/5/5 13:23
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set(){
        threadLocal.set(Thread.currentThread().getId());
    }

    public long get(){
        //当ThreadLocal返回null的时候，此时null进行拆箱操作，就会有空指针的异常，这个问题是程序员编码的时候的问题，并不是ThreadLocal造成的
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread = new Thread(()->{
            threadLocalNPE.set();
            System.out.println(threadLocalNPE.get());
        });
        thread.start();
    }
}
