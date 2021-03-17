package com.zenghm.algorithm;

import java.util.concurrent.Semaphore;

/**
 * @author Airlen
 * @date 2021/3/17
 * @description xxx
 */
public class IssueAndSellTickets_Semaphore {
    private static Semaphore providerTicketCount = new Semaphore(10);
    private static Semaphore consumerTicketCount = new Semaphore(0);
    //private static volatile int ticketCount = 0;
    static class Provider extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    providerTicketCount.acquire();
                    System.out.println("产生一张票:"+providerTicketCount.availablePermits());
                    consumerTicketCount.release();
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
                    consumerTicketCount.acquire();
                    System.out.println("消费一张票:"+consumerTicketCount.availablePermits());
                    providerTicketCount.release();
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
