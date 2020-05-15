package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示一个Future的使用方法
 * @Author: Mark
 * @CreateDate: 2020/2/5 14:40
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class OneFuture {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Future<Integer> future = executorService.submit(new CallableTask());
        try {
            /*
            获取子线程返回的值：当线程池中的线程还没执行完时，通过get（）获取运行结果时，当前线程会阻塞，
            直到子线程返回结果
             */
            log.info("获取CallableTask的执行结果");
            Integer result = future.get();
            log.info("CallableTask的执行结果：{}",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //关闭线程池
        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            //通过睡眠 模拟task的执行
            Thread.sleep(5000);
            return new Random().nextInt();
        }
    }
}
