package com.mark.csdn.concurrent.aqs.locks.condition;

import java.util.PriorityQueue;

/**
 * @Description: 基于synchronized生产者和消费者
 * @Author: Mark
 * @CreateDate: 2020/4/21 17:32
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class ConsumerAndProduce {

    private final int queueSize = 10;
    /** 存放生产元素的队列，队列容量指定为10 **/
    private final PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    private static final Object lock = new Object();

    public static void main(String[] args) {
        ConsumerAndProduce consumerAndProduce = new ConsumerAndProduce();
        Producer producer = consumerAndProduce.new Producer();
        Consumer consumer = consumerAndProduce.new Consumer();
        producer.start();
        consumer.start();
    }

    private class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (lock) {
                    while (queue.size() == 0) { //队列为空
                        System.out.println("队列空，等待数据");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //消费一个商品
                    queue.poll();
                    System.out.println("从队列里取走了一个数据，队列剩余" + queue.size() + "个元素");
                }
            }
        }
    }

   private  class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (lock) {
                    if(queue.size() != queueSize){
                        queue.offer(1);
                        lock.notifyAll();
                        System.out.println("向队列插入了一个元素，队列剩余空间" + (queueSize - queue.size()));
                    }
                }
            }
        }
    }

}
