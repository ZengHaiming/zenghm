package com.zenghm.network.m1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create date:18/6/2.
 * Created by: zhm.
 * Class name:M1SocketClient.
 */

public class M1SocketClient {
    Logger logger = LoggerFactory.getLogger(M1SocketClient.class);
    private String ip;
    private int port;
    //private Socket socket;
    private int timeout;

    public M1SocketClient(String ip, int port, int timeout) throws IOException {
        this.ip = ip;
        this.port = port;
        this.timeout = timeout;
    }

    public String sendMsg(String msg) {
        try (Socket socket = new Socket()) {
            //socket.setSoTimeout();
            socket.setReuseAddress(true);
            socket.connect(new InetSocketAddress(this.ip,this.port),this.timeout);
            try (OutputStream os = socket.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 BufferedWriter bw = new BufferedWriter(osw);
                 InputStream is = socket.getInputStream();
                 InputStreamReader isw = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isw)) {
                bw.write(msg, 0, msg.length());
                bw.flush();
                socket.shutdownOutput();
                StringBuilder resp = new StringBuilder(128);
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    resp.append(currentLine);
                }
                return resp.toString();
            } /*catch (IOException e) {
                logger.error(e.getMessage(), e);
            }*/
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}
