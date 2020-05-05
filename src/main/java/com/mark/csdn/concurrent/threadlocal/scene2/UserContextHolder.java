package com.mark.csdn.concurrent.threadlocal.scene2;


import com.mark.csdn.concurrent.threadlocal.ThreadLocalSceneExample1;

/**
 * @Description: 上下文
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:41
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 * @see ThreadLocalSceneExample1
 */
public class UserContextHolder {

    /**
     * 注意此时ThreadLocal的声明方式
     */
    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

}
