package com.zenghm.network.m1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Create date:18/6/2.
 * Created by: zhm.
 * Class name:M1SocketServer.
 */

public class M1SocketServer {
    private Logger logger = LoggerFactory.getLogger(M1SocketClient.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int port;

    public M1SocketServer(int port) {
        this.port = port;
    }

    public void startListen() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(this.port));
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new RespTask(socket));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    class RespTask implements Runnable {
        private Socket socket;

        public RespTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream is = socket.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr);
                 OutputStream os = socket.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 BufferedWriter bw = new BufferedWriter(osw)) {
                String currentLine ;
                StringBuilder reqSb = new StringBuilder(128);
                while ((currentLine=br.readLine())!=null){
                    reqSb.append(currentLine);
                }
                socket.shutdownInput();
                logger.info(reqSb.toString());
                bw.write("数据接受完成，响应时间："+System.currentTimeMillis());
                bw.flush();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}
