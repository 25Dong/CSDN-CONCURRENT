package com.mark.csdn.concurrent.threadlocal.scene2;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 控制器
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:46
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class UserController {

    public void process(){
        //获取当前用户名
        User user = UserContextHolder.userThreadLocal.get();
        log.info(user.toString());

        //模拟链路调用
        new UserService().process();
    }
}
