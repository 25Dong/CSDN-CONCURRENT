package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示批量提交任务时，用List来批量接收结果
 * @Author: Mark
 * @CreateDate: 2020/2/5 15:02
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class MultiFutures {

    private static final int THREAD_NUM =10;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static Callable<Integer> callable = () -> {
        Thread.sleep(5000);
        return new Random().nextInt();
    };

    public static void main(String[] args) {
        List<Future<Integer>> futureList = new ArrayList<>();

        //通过想线程池 执行10个子任务
        for (int i = 0; i < THREAD_NUM; i++) {
            Future<Integer> future = executorService.submit(callable);
            futureList.add(future);
        }


        /*
        打印子任务的执行结果 :
        main线程 打印结果时 如果子线程还没有执行完 main线程会先阻塞 等待子线程执行 才会拿到执行结果 继续往下执行
        这里的打印结果是 ：main线程先阻塞5秒（子线程 睡眠5秒），然后打印结果。注意 因为10个子线程是一起执行的

        这里有个缺点，如果for循环中的第一个get执行时间很长，哪怕后面的线程执行结果已经完成了，还是需要等待第一个for
        循环运行结束，然后进入下一个循环
         */
        log.info("print result -----");
        for(Future<Integer> future : futureList){
            try {
                Integer result = future.get();
                log.info("result is "+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
