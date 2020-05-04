package com.mark.csdn.concurrent.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 对象逃逸
 * @Author: Mark
 * @CreateDate: 2020/4/22 22:57
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
        System.out.println("Escape 还没初完成");
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
            System.out.println("Escape 还没初始化完成");
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
