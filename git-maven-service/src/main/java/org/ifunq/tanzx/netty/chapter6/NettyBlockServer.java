package org.ifunq.tanzx.netty.chapter6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Netty阻塞网络处理
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettyBlockServer {

    /**
     * 简单的接收客户端连接，向客户端发生Hi，并关闭连接
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        // 使用NioEventLoopGroup以允许非阻塞模式(旧的N/O)
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(8090));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        // 将消息记录到控制台
                        System.out.println("Server received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        String inMsg = in.readLine();
                        ByteBuf msgBuf = Unpooled.copiedBuffer(inMsg, CharsetUtil.UTF_8);
                        ctx.writeAndFlush(msgBuf);
                    }
                });

            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}