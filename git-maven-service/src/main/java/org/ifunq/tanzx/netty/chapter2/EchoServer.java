package org.ifunq.tanzx.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Echo处理Server
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-31 18:11
 **/
public class EchoServer {

    public static void main(String[] args) throws InterruptedException {

        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
            // 指定所使用的NIO传输Channel
            .channel(NioServerSocketChannel.class)
            .localAddress(new InetSocketAddress(8090))
            .childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                // 对于所有客户端连接来说，都会使用同一个EchoServerHandler
                socketChannel.pipeline().addLast(echoServerHandler);
            }
        });
        ChannelFuture f = b.bind().sync();
        f.channel().close().sync();
        // 关闭EventLoopGroup，释放所有资源
        group.shutdownGracefully().sync();
    }
}


