package com.mark.csdn.concurrent.threadlocal.scene2;

import lombok.Data;

/**
 * @Description: 用户实例，买个线程信息不用
 * @Author: Mark
 * @CreateDate: 2019/12/28 12:40
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Data
public class User {

    /**
     * 用户名
     */
    private String name;

    public User(String username) {
        this.name = username;
    }
}
