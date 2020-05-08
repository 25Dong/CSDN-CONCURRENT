package com.mark.csdn.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description: 查看一个普通的Java程序包含了那些线程
 * @Author: Mark
 * @CreateDate: 2020/3/7 11:47
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 摘抄于《Java并发编程的艺术-4.1.1》
 * 总结：一个Java程序的运行不仅仅是main()方法的运行，而是main线程和其他线程同时执行
 */
public class MultiThread {

    public static void main(String[] args) {
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程和堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历打印线程信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
            //[6]Monitor Ctrl-Break
            //[5]Attach Listener
            //[4]Signal Dispatcher
            //[3]Finalizer
            //[2]Reference Handler
            //[1]main
        }
    }
}
