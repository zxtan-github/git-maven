package org.ifunq.tanzx.netty.chapter11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.InetSocketAddress;

/**
 * Netty Http-服务器端
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettyHttpServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(8090));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                // ch.pipeline().addLast(new HttpResponseEncoder());
                // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                // ch.pipeline().addLast(new HttpRequestDecoder());
                // server端接收的HttpContext聚合完成以后组成FullHttpRequest向下传递
                ch.pipeline().addLast(new HttpServerCodec());
                // server端接收的HttpContext聚合设定一个512KB大小缓冲区来进行聚合操作，必须要配置
                ch.pipeline().addLast(new HttpObjectAggregator(512 * 1024));
                // 服务器端压缩发送给客户端的数据，解压缩用HttpContentDecompressor
                ch.pipeline().addLast(new HttpContentCompressor());
                // 最终request处理逻辑
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        FullHttpRequest request = null;
                        if (msg instanceof FullHttpRequest) {
                            request = (FullHttpRequest) msg;
                            String uri = request.uri();
                            System.out.println("Uri:" + uri);
                            HttpMethod httpMethod = request.method();
                            System.out.println("Method:" + httpMethod.name());
                            ByteBuf buf = request.content();
                            System.out.println("Content:" + buf.toString(io.netty.util.CharsetUtil.UTF_8));
                            request.release();
                        }
                        //建立httpResponse
                        String res = "{result:true,name:tanzongxi,age:29}";
                        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                                Unpooled.wrappedBuffer(res.getBytes()));
                        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
                        httpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, 11);
                        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
                        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                        ctx.writeAndFlush(httpResponse);
                    }
                });
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}