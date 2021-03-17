package com.zenghm.algorithm;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Airlen
 * @date 2021/3/17
 * @description 生产者消费票 ， 消费则消费票 ,使用堵塞队列
 */
public class IssueAndSellTickets_BlockList {
    private static ArrayBlockingQueue<Ticket> blockingQueue = new ArrayBlockingQueue<>(10);
    private static class Ticket{
        //static volatile int count = 0;
    }
    static class Provider extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    blockingQueue.put(new Ticket());
                    System.out.println("产生一张票后剩余票数:"+blockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("消费前剩余票数:"+blockingQueue.size());
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Provider provider = new Provider();
        Consumer consumer = new Consumer();
        provider.start();
        consumer.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread.interrupted();
    }
}
