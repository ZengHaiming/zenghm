package com.zenghm.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create date:2018/05/13.
 * Created by: Airlen.
 * Class name:ZSocketServer.
 */
public class ZSocketServer {
    public static void main(String[] args) throws IOException {
        int port = 9282;
        ServerSocket server = null;
        //server =

        Socket socket = null;
        while (true){
            socket = server.accept();
            //new Thread(new )
        }
    }

    class TimeServerHandler implements Runnable{
        Socket socket;
        public TimeServerHandler(Socket socket){

        }
        public void run() {
        }
    }
}
