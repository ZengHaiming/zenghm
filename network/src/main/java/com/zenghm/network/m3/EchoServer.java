package com.zenghm.network.m3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import io.netty.channel.socket.SocketChannel;

/**
 * Create date:18/6/3.
 * Created by: zhm.
 * Class name:EchoServer.
 */

public class EchoServer {
    private static Logger logger = LoggerFactory.getLogger(EchoServer.class);
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
    public static void main(String[] args){
        if(args.length!=1){
            logger.error(String.format("Usage: %1$s <port>",EchoServer.class.getSimpleName()));
            return;
        }
        int p = Integer.valueOf(args[0]);
        new EchoServer(p).start();
    }
    public void start(){
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(serverHandler);
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind().sync();
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
