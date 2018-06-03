package com.zenghm.network.m3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Create date:18/6/3.
 * Created by: zhm.
 * Class name:EchoClient.
 */

public class EchoClient {
    private static Logger logger = LoggerFactory.getLogger(EchoServer.class);
    private final String host;
    private final int port;
    public EchoClient(String host,int port) {
        this.host = host;
        this.port = port;
    }
    public static void main(String[] args){
        if(args.length!=2){
            logger.error(String.format("Usage: %1$s <host> <port>",EchoServer.class.getSimpleName()));
            return;
        }
        String h = args[0];
        int p = Integer.valueOf(args[1]);
        new EchoClient(h,p).start();
    }
    public void start(){
        final EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host,port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(clientHandler);
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}
