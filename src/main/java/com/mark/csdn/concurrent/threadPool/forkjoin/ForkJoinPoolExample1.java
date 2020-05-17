package com.mark.csdn.concurrent.threadPool.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @Description: ForkJoinPool，Fork，拆解你的任务。第二个是 Join，合并任务的执行结果。Fork/Join 的核心思想就是分而治之。
 * @Author: Mark
 * @CreateDate: 2020/5/17 10:58
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class ForkJoinPoolExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Task task = new Task(1, 10000);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
        log.info("计算结果为：" + submit.get());
        forkJoinPool.shutdown();
    }

    private static class Task extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 100;
        private int from;
        private int to;

        public Task(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Integer compute() {
            if(THRESHOLD > (to -from)){
                return IntStream.range(from, to + 1)
                        .reduce((a, b) -> a + b)
                        .getAsInt();
            }else{
                int forkNumber = (from + to) / 2;
                Task left = new Task(from, forkNumber);
                Task right = new Task(forkNumber+1, to);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }

}
