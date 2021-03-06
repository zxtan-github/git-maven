package org.ifunq.tanzx.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Echo处理Server
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-31 18:11
 **/
// Sharable表示此对象在channel间共享，没有这个就只能一个channel连接
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 将消息记录到控制台
        System.out.println("Server received: " + byteBuf.toString(CharsetUtil.UTF_8));
        // 将接收到的消息发重新又写给发送者，而不冲刷出站消息
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点并关闭该Channel
        // flush掉所有写回的数据 当flush完成后关闭channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常并关闭该Channel
        cause.printStackTrace();
        ctx.close();
    }
}


