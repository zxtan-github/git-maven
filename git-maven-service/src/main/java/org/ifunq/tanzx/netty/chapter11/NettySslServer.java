package org.ifunq.tanzx.netty.chapter11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

/**
 * Netty SSL单向认证-服务器端
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettySslServer {

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
        // SslContextBuilder.forServer(PrivateKey key, String keyPassword, X509Certificate... keyCertChain)

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        // 使用NioEventLoopGroup以允许非阻塞模式(旧的N/O)
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(8090));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // 添加SSL处理handler
                SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
                ch.pipeline().addFirst("001", new SslHandler(sslEngine));
                ch.pipeline().addLast("002", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        // 将消息记录到控制台
                        System.out.println("Server received: " + byteBuf.toString(CharsetUtil.UTF_8));
                    }
                });
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}