package org.ifunq.tanzx.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-31 19:10
 **/
public class EchoClinetHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当被服务器通知channel是活跃的是，向服务器发送一条消息
        ctx.write("Netty rocks!");
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        // 将从服务器收到消息记录到控制台
        System.out.println("Client received: " + byteBuf.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常并关闭该Channel
        cause.printStackTrace();
        ctx.close();
    }
}