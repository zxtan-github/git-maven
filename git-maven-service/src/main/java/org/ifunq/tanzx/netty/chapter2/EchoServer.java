package org.ifunq.tanzx.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

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
        // 通过nio方式来接收连接和处理连接
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap(); // 引导辅助程序
        b.group(group)
                // 指定所使用的NIO传输Channel
                // 设置nio类型的channel
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(8090))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
        ChannelFuture f = b.bind().sync();
        // 应用程序会一直等待，直到channel关闭
        // 这是一段阻塞的代码，除非链路断了，否则是不会终止的，我们可以在handler中手动关闭，达到关闭客户端的效果
        f.channel().closeFuture().sync();
        // 关闭EventLoopGroup线程池，释放所有资源
        group.shutdownGracefully().sync();
    }
}


