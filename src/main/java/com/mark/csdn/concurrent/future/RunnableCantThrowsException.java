package com.mark.csdn.concurrent.future;

/**
 * @Description: run方法中无法抛出checked Exception
 * @Author: Mark
 * @CreateDate: 2020/2/5 14:37
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class RunnableCantThrowsException {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //不能将异常抛出，只能内部try catch 处理
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
