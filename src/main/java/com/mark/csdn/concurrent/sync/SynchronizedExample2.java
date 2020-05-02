package com.mark.csdn.concurrent.sync;

import com.mark.csdn.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: synchronized修饰类和静态代码块，作用于所有的对象
 * @CreateDate: 2019/6/30 0:00
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample2 {

    /**
     * synchronized修饰class
     * @param objName 实例名称
     */
    public  void lockBlock(String objName){
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("修饰类 =>{}-{}",objName,i);
            }
        }
    }

    /**
     * synchronized修饰一个静态方法
     * @param objName 实例名称
     */
    public static synchronized void lockMethod(String objName){
        for (int i = 0; i < 10; i++) {
            log.info("修饰静态方法 =》{}-{}",objName,i);
        }
    }
}
