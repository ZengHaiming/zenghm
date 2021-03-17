package com.zenghm.algorithm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，一个打印奇数 ，一个打印偶数
 */
public class NumberPrint {
    private static Lock lock = new ReentrantLock();
    private static int state = 1;

    static class ThreadPrintOddNumber extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <=50;) {
                try {
                    lock.lock();
                    while (state % 2 == 1) {
                        System.out.println(state);
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadEvenNumber extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <=50;) {
                try {
                    lock.lock();
                    while (state % 2 == 0) {
                        System.out.println(state);
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        new NumberPrint.ThreadPrintOddNumber().start();
        new NumberPrint.ThreadEvenNumber().start();
    }
}
