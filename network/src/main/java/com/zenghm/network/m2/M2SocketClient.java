package com.zenghm.network.m2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Create date:18/6/9.
 * Created by: zhm.
 * Class name:M2SocketClient.
 * 错误代码，仅用于实验 ，不知怎么写
 */

public class M2SocketClient {
    private Logger logger = LoggerFactory.getLogger(M2SocketClient.class);
    private final String host;
    private final int port;

    public M2SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String request(String msg) throws IOException {
        String resp = "";
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(this.host,this.port);
        socketChannel.connect(address);
        //Selector selector = Selector.open();
        final ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        //socketChannel.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,byteBuffer.duplicate());
        int writeLength;
        do {
            writeLength = socketChannel.write(byteBuffer);
        }while (writeLength>0);
        socketChannel.shutdownOutput();
        StringBuilder respSb = new StringBuilder(1024);
        int msgLength;
        do {
            ByteBuffer respByteBuffer = ByteBuffer.allocate(1024);
            msgLength = socketChannel.read(respByteBuffer);
            if(msgLength>-1){
                byte[] bytes = new byte[msgLength];
                respByteBuffer.get(bytes);
                respSb.append(new String(bytes));
            }
        }while (msgLength>0-1);
        logger.info(respSb.toString());
        socketChannel.close();
        /*while (true){
            try {
                selector.select();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                break;
            }
            Set<SelectionKey> readyKey = selector.selectedKeys();
            Iterator<SelectionKey> iterator =readyKey.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey  = iterator.next();
                iterator.remove();
                try {
                    if(selectionKey.isWritable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        while (buffer.hasRemaining()){
                            if(client.write(buffer)==0){
                                break;
                            }
                        }
                    }
                    if(selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        StringBuilder req = new StringBuilder(1024);
                        int msgLength;
                        do {
                            ByteBuffer respByteBuffer = ByteBuffer.allocate(1024);
                            msgLength = client.read(respByteBuffer);
                            if(msgLength>0){
                                byte[] bytes = new byte[msgLength];
                                respByteBuffer.get(bytes);
                                req.append(new String(bytes));
                            }
                        }while (msgLength>0);
                        logger.info(req.toString());
                        client.close();
                        //接受响应退出
                        //break;
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                    selectionKey.cancel();
                    try {
                        selectionKey.channel().close();
                    } catch (IOException e1) {
                        logger.error(e1.getMessage(),e1);
                    }
                }
            }

        }*/
        return respSb.toString();
    }
}
