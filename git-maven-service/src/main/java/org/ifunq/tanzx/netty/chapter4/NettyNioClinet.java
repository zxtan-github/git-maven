package org.ifunq.tanzx.netty.chapter4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Netty阻塞网络处理
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettyNioClinet {

    /**
     * 简单的接收客户端连接，想客户端发生Hi，并关闭连接
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Server Hi\r\n", CharsetUtil.UTF_8));
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        // 使用NioEventLoopGroup以允许非阻塞模式(旧的N/O)
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("localhost", 8090));
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        // 将消息记录到控制台
                        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ctx.writeAndFlush(byteBuf);
                    }
                });
            }
        });
        ChannelFuture channelFuture = bootstrap.connect().sync();
        Channel clientChannel  = channelFuture.channel();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msg = in.readLine();
                ByteBuf msgBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
                clientChannel.writeAndFlush(msgBuf);
            }
        } catch (Exception e) {
            channelFuture.channel().closeFuture().sync();
            group.shutdownGracefully().sync();
        }

    }
}