package com.mark.csdn.concurrent.singleton;


import com.mark.csdn.concurrent.annotations.Recommend;
import com.mark.csdn.concurrent.annotations.ThreadSafe;

/**
 * @Description: 通过枚举实现单例
 * @Author: Mark
 * @CreateDate: 2019/6/30 12:13
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){}

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 instance;

        /**
         *  JVM保证这个方法绝对只调用一次
         */
        Singleton(){
            instance = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return instance;
        }
    }

}
