package com.mark.csdn.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description: 演示get的超时方法，需要注意超时后处理，调用future.cancel()。
 *               演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 * @Author: Mark
 * @CreateDate: 2020/2/5 15:22
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class Timeout {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    static class CallableTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.info("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票哪家强？找某程");
        }
    }

    static class Ad {

        private String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private Ad taskExce(){
        Ad ad;
        Future<Ad> adFuture = executorService.submit(new CallableTask());
        try {
            ad = adFuture.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时 的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时 时候的默认广告");
            log.warn("超时 未获取到广告");

            //true：子任务收收到一个中断信号，在我这个类中会打印：sleep期间被中断了；false则相反
            //true适用于子任务能够有能力处理中断
            //boolean cancel = adFuture.cancel(true);

            /*实际应该中建议使用false
            *false仅用于避买呢启动尚未启动的任务，适用于
            * 1.未能处理interrupt的任务
            * 2.不清楚任务是否支持取消
            * 3.需要等待已经开始的任务执行完成
            */
            boolean cancel = adFuture.cancel(false);
            log.info("超时 cancel 的结果" + cancel);
        }
        executorService.shutdown();
        return ad;
    }

    public static void main(String[] args) {
        Ad ad = new Timeout().taskExce();
        System.out.println(ad);
    }
}
