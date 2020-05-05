package com.mark.csdn.concurrent.threadlocal.scene2;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: service
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:49
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class UserService {

    public void process(){
        //获取当前用户名
        User user = UserContextHolder.userThreadLocal.get();
        log.info(user.toString());
    }
}
