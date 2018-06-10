package com.zenghm.network.m2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Create date:18/6/9.
 * Created by: zhm.
 * Class name:M2SocketServer.
 */

public class M2SocketServer {
    private Logger logger = LoggerFactory.getLogger(M2SocketServer.class);
    private final int port;

    public M2SocketServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(this.port);
        serverSocket.bind(address);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hello World! \n".getBytes());
        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                break;
            }
            Set<SelectionKey> readyKey = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKey.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        logger.info("Accepted connection from " + client);
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        //ByteBuffer[] byteBuffer = new ByteBuffer[200];
                        StringBuilder req = new StringBuilder(1024);
                        int msgLength;
                        do {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            msgLength = client.read(byteBuffer);
                            if(msgLength>-1){
                                byte[] bytes = new byte[msgLength];
                                byteBuffer.get(bytes);
                                req.append(new String(bytes));
                            }
                        }while (msgLength>-1);
                        logger.info(req.toString());

                        /*
                        非堵塞不能用堵塞的方式读取信息
                        try (InputStream is = socket.getInputStream();
                             InputStreamReader isr = new InputStreamReader(is);
                             BufferedReader br = new BufferedReader(isr)) {
                            String currentLine;
                            StringBuilder reqSb = new StringBuilder(128);
                            while ((currentLine = br.readLine()) != null) {
                                reqSb.append(currentLine);
                            }
                            logger.info(reqSb.toString());
                        }*/
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                        logger.error(e1.getMessage(), e1);
                    }
                }
            }
        }
    }
}
