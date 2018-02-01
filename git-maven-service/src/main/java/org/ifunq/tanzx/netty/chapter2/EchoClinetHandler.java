package org.ifunq.tanzx.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-31 19:10
 **/
@ChannelHandler.Sharable
public class EchoClinetHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     *此方法会在连接到服务器后被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当被服务器通知channel是活跃的是，向服务器发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     *此方法会在接收到服务器数据后调用
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        // 将从服务器收到消息记录到控制台
        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     *捕捉到异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常并关闭该Channel
        cause.printStackTrace();
        ctx.close();
    }
}