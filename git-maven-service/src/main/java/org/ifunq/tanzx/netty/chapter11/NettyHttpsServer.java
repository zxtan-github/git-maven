package org.ifunq.tanzx.netty.chapter11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

/**
 * Netty Http-服务器端
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettyHttpsServer {

    public static void main(String[] args) throws Exception {
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        // 新建一个密钥库实例
        KeyStore ks = KeyStore.getInstance("JKS");
        // 输入密码打开密钥库
        ks.load(new FileInputStream("D:/ifunq/支付申报/宝付/ssl/test.keystore"), "123456".toCharArray());
        // 输入密钥密码？
        kmf.init(ks, "123456".toCharArray());
        // 以keystore的形式创建服务器端的SslContext，还可以私钥文件等形式
        // ClientAuth.NONE表示不需要客户端认证，即单向认证
        final SslContext sslContext = SslContextBuilder.forServer(kmf).clientAuth(ClientAuth.NONE).build();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(8090));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // 添加SSL处理handler
                SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
                ch.pipeline().addFirst("001", new SslHandler(sslEngine));
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
                        }
                        //建立httpResponse
                        String res = "{result:true,name:tanchengyu,age:29}";
                        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                                Unpooled.wrappedBuffer(res.getBytes()));
                        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
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