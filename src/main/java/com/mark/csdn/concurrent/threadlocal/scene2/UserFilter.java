package com.mark.csdn.concurrent.threadlocal.scene2;

/**
 * @Description: 拦截器
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:43
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class UserFilter {

    public void process(){
        //拦截器，拦截获取用户名
        String username = Thread.currentThread().getName();

        //将用户名保存到ThreadLocal中
        UserContextHolder.userThreadLocal.set(new User(username));

        //模拟链路调用
        new UserController().process();
    }
}
