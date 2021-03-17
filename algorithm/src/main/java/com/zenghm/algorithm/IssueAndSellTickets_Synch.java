package com.zenghm.algorithm;

/**
 * @author Airlen
 * @date 2021/3/17
 * @description xxx
 */
public class IssueAndSellTickets_Synch {
    private static volatile byte[] lock = new byte[0];
    private static volatile Integer count = 0;
    static class Provider extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    while (count==10){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("产生一张票后剩余票数:"+count);
                    lock.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    while (count==0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("消费前剩余票数:"+count);
                    count--;
                    lock.notifyAll();
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
