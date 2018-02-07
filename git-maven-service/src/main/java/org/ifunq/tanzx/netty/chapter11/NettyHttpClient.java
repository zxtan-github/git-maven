package org.ifunq.tanzx.netty.chapter11;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Netty Http-客户端
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-07 18:07
 **/
public class NettyHttpClient {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("localhost", 8090));
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                // ch.pipeline().addLast(new HttpResponseDecoder());
                // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                // ch.pipeline().addLast(new HttpRequestEncoder());
                // 客户端接收的HttpContext聚合完成以后组成FullHttpResponse向下传递
                ch.pipeline().addLast(new HttpClientCodec());
                // 客户端接收的HttpContext聚合设定一个512KB大小缓冲区来进行聚合操作，必须要配置
                ch.pipeline().addLast(new HttpObjectAggregator(512 * 1024));
                // 客户端解压缩收到的数据，服务器端压缩用HttpContentCompressor
                ch.pipeline().addLast(new HttpContentDecompressor());

                ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        FullHttpResponse response = null;
                        if (msg instanceof FullHttpResponse) {
                            response = (FullHttpResponse) msg;
                            HttpResponseStatus status = response.status();
                            System.out.println("Status:" + status.toString());
                            HttpHeaders headers = response.headers();
                            System.out.println("HttpHeaders:" + headers.toString());
                            ByteBuf buf = response.content();
                            System.out.println("Content:" + buf.toString(io.netty.util.CharsetUtil.UTF_8));
                        }
                    }
                });
            }
        });
        ChannelFuture channelFuture = bootstrap.connect().sync();
        Channel clientChannel  = channelFuture.channel();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msg = in.readLine();
            URI uri = new URI("http://localhost:8090");
            String context = "aaa=tttt&name=tzx";
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(context.getBytes("UTF-8")));

            // 构建http请求
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            clientChannel.writeAndFlush(request);
            if ("exit".equals(msg)) {
                break;
            }
        }
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();

    }
}