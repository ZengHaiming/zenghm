package com.zenghm.network.m1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create date:18/6/2.
 * Created by: zhm.
 * Class name:M1SocketClientTest.
 */
public class M1SocketClientTest {
    private Logger logger = LoggerFactory.getLogger(M1SocketClient.class);
    private CountDownLatch downLatch = new CountDownLatch(12000);
    @org.junit.Test
    public void clientTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int i =0,count=12000;
        long begin = System.currentTimeMillis();
        do{
            i++;
            executorService.execute(new  ReqTask());
            //ReqTask task = new ReqTask();
            //task.run();
        }while (i<count);
        downLatch.await();
        long end =System.currentTimeMillis();
        logger.debug("总执行时间："+(end-begin));
    }

    class ReqTask implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            String msg  = String.format("Thread name %1$s:first line !\n Thread name %1$s:second  line ! \n Thread name %1$s:third  line ! \n",name);
            try {
                M1SocketClient client = new M1SocketClient("127.0.0.1",9778,1000*60*3);
                //java.net.BindException: Address already in use: connect
                //1、电脑端口消耗尽则会出现上面异常
                //2、可以修改电脑注册表，修改tcp 可用端口范围
                String respMsg = client.sendMsg(msg);
                logger.info(respMsg);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            downLatch.countDown();
        }
    }
}
