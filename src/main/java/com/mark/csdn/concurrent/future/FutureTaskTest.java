package com.mark.csdn.concurrent.future;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Mark
 * @CreateDate: 2020/4/10 12:10
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //通过Callable实例构建FutureTask实例
        FutureTask<String> futureTask = new FutureTask<>(new Task());
        //提交任务到线程池
        executorService.execute(futureTask);
        //取出子线程运行结果
        String result = futureTask.get();
        System.out.println("子任务的执行结果：" + result);//子任务的执行结果：1995-01-02
    }

    private static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "1995-01-02";
        }
    }

}
