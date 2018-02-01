package org.ifunq.tanzx.netty.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-31 19:10
 **/
public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("localhost",8090));
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new EchoClinetHandler());
            }
        });
        ChannelFuture f = bootstrap.connect().sync();
        f.channel().closeFuture().sync();
        // 关闭EventLoopGroup线程池，释放所有资源
        group.shutdownGracefully().sync();
    }
}