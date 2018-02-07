package org.ifunq.tanzx.netty.chapter11;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Base64;
import org.ifunq.tanzx.ssl.SampleEncryptAndSign;
import sun.security.x509.X509CertImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Netty SSL单向认证-客户端
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-01 11:49
 **/
public class NettySslClinet {

    public static void main(String[] args) throws Exception {

        // 证书Base64字符串
        byte[] keyBytes= Base64.decodeBase64(SampleEncryptAndSign.PUBLIC_CER);
        X509CertImpl x509Cert = new X509CertImpl(keyBytes);
        final SslContext sslContext = SslContextBuilder.forClient().trustManager(x509Cert).build();

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        // 使用NioEventLoopGroup以允许非阻塞模式(旧的N/O)
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("localhost", 8090));
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // 添加SSL处理handler
                ch.pipeline().addFirst(new SslHandler(sslContext.newEngine(ch.alloc())));
                ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        // 将消息记录到控制台
                        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
                    }
                });
            }
        });
        ChannelFuture channelFuture = bootstrap.connect().sync();
        Channel clientChannel  = channelFuture.channel();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msg = in.readLine();
            ByteBuf msgBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
            clientChannel.writeAndFlush(msgBuf);
            if ("exit".equals(msg)) {
                break;
            }
        }
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();

    }
}