package com.geekbang.JVM.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月01日 20:50
 */
@Data
public class HttpInboundServer {
    private int port;

    private List<String> proxyServers;

    public HttpInboundServer(int port, List<String> proxyServers) {
        this.port = port;
        this.proxyServers = proxyServers;
    }

    public void run() throws InterruptedException {
        // 第一个线程组是用于接收Client端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 第二个线程组是用于实际的业务处理的
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128) // TCP的缓冲区设置
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024) // 设置接收缓冲区大
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲的大小
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持连续
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // 绑定两个线程池
            // 指定NIO的模式，如果是客户端就是NioSocketChannel
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpInboundInitializer(this.proxyServers));

            Channel ch = b.bind(port).sync().channel(); // 绑定端口
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync(); //等待关闭(程序阻塞在这里等待客户端请求)
        } finally {
            bossGroup.shutdownGracefully(); // 关闭线程
            workerGroup.shutdownGracefully(); // 关闭线程
        }
    }
}
