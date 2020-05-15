package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 用FutureTask创建Future
 * @Author: Mark
 * @CreateDate: 2020/2/5 22:22
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class FutureTaskDemo {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            return new Random().nextInt();
        }
    }

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableTask());

        //方式一 通过Thread类启动一个线程
        new Thread(futureTask).start();

        //方式二 通过线程池启动一个子线程
        //executorService.submit(futureTask);

        try {
            //打印 子线程运行结果
            Integer result = futureTask.get();
            log.info("result is " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
