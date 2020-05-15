package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description: 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：并不是说一产生异常就抛出，直到我们get执行时，才会抛出
 * @Author: Mark
 * @CreateDate: 2020/2/5 16:27
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class GetException {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Future<Integer> future = executorService.submit(new CallableTask());

        //在运行下面的语句的时候其实，子任务早就已经执行完毕了。但是get的时候才会抛出异常
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            //即使子任务抛出异常，也是返回了true的。也就是说抛出异常也当作一种执行完毕的结果
            System.out.println("子任务是否已经执行完毕："+future.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws IllegalArgumentException {
            log.error("抛出异常。。。。");
            throw new IllegalArgumentException();
        }
    }
}
