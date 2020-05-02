package com.mark.csdn.concurrent.future;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Mark
 * @CreateDate: 2020/4/10 11:58
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
        */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Task());//submit结果
        String result = future.get();//取出子线程运行结果
        System.out.println("子任务的执行结果："+result);//子任务的执行结果：1995-01-01
        executorService.shutdown();
    }

    private static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "1995-01-01";
        }
    }

}
