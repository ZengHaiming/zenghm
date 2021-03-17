package com.zenghm.algorithm;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Airlen
 * @date 2021/3/1
 * @description 通过管道进行多线程通信
 */
public class PipedReaderWriter {
    static class Sender implements Runnable{
        private Random random = new Random(47);
        private PipedWriter out = new PipedWriter();
        public PipedWriter getPipedWriter(){ return out; }
        @Override
        public void run(){
            try{
                while(true){
                    for(char c = 'A'; c<'Z'; c++){
                        out.write(c);
                        //随机休眠500以内毫秒
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                    }
                }
            }catch(IOException e){
                System.out.println(e + " Sender write exception");
            }catch(InterruptedException e){
                System.out.println(e + " Sender sleep interrupt");
            }
        }
    }

    static class Receiver implements Runnable{
        private PipedReader in;
        public Receiver(Sender sender) throws IOException{
            in = new PipedReader(sender.getPipedWriter());
        }

        @Override
        public void run(){
            try{
                while(true){
                    System.out.println("Read: " + (char)in.read() + ",");
                }
            }catch(IOException e){
                System.out.println(e + " Receiver read exception");
            }
        }
    }
    public static class PipedIO {
        public static void main(String[] args) throws Exception{
            Sender sender = new Sender();
            Receiver receiver = new Receiver(sender);
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(sender);
            exec.execute(receiver);
            //休眠4秒钟后中断
            TimeUnit.SECONDS.sleep(4);
            exec.shutdownNow();

        }
    }
}
