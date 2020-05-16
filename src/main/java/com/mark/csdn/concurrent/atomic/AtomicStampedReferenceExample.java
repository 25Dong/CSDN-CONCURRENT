package com.mark.csdn.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description: 带上版本号的更新，为了解决ABA问题
 * @Author: Mark
 * @CreateDate: 2020/5/16 14:24
 * @Copyright : 豆浆油条个人非正式工作室
 * @see AtomicStampedReference
 */
public class AtomicStampedReferenceExample {

    public static void main(String[] args) {
        AtomicStampedReference<String> atomic = new AtomicStampedReference<>("test1",0);
        boolean result = atomic.compareAndSet("test", "test2", 0, 1);
        System.out.println(result);
        result = atomic.compareAndSet("test1", "test2", 0, 1);
        System.out.println(result);
        System.out.println("reference is "+atomic.getReference());
        System.out.println("stamp is "+atomic.getStamp());

    }
}
