package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示一个Future的使用方法,Lambda形式
 * @Author: Mark
 * @CreateDate: 2020/2/5 14:55
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class OneFutureLambda {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //通过Lambda表达式声明一个Callable实例
        Callable<Integer> callable = ()->{
            Thread.sleep(5000);
            return new Random().nextInt();
        };


        Future<Integer> future = executorService.submit(callable);
        try {
            Integer result = future.get();
            log.info("result is {}",result );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //关闭线程池
        executorService.shutdown();
    }
}
