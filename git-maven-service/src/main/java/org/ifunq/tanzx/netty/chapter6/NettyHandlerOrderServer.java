package org.ifunq.tanzx.netty.chapter6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * handler顺序测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-03 11:49
 **/
public class NettyHandlerOrderServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        // 使用NioEventLoopGroup以允许非阻塞模式(旧的N/O)
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(8090));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("Last001", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("Last001 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addLast("Last002", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("Last002 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("Last003 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("Last004 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                // 设定一个同名的HandlerAdapter,看是否覆盖，
                // 会报错java.lang.IllegalArgumentException: Duplicate handler name: Last002
                // ch.pipeline().addLast("Last002", new ChannelInboundHandlerAdapter() {});
                ch.pipeline().addFirst("First001", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("First001 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addFirst("First002", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("First002 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("First003 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
                ch.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("First004 received: " + byteBuf.toString(CharsetUtil.UTF_8));
                        ctx.fireChannelRead(msg);
                    }
                });
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}