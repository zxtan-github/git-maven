package org.ifunq.tanzx.ssl;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;

/**
 * Java SSL单向认证服务端
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-06 11:14
 **/
public class SampleOnewaySslServer {

    public static void main(String[] args) throws Exception {
        SSLContext ctx = SSLContext.getInstance("SSL");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        // 新建一个密钥库实例
        KeyStore ks = KeyStore.getInstance("JKS");
        // 输入密码打开密钥库
        ks.load(new FileInputStream("D:/ifunq/支付申报/宝付/ssl/test.keystore"), "123456".toCharArray());
        // 输入密钥密码？
        kmf.init(ks, "123456".toCharArray());
        // 初始化SSLContext上下文
        ctx.init(kmf.getKeyManagers(), null, null);
        // 创建服务端Socket
        SSLServerSocket sslServerSocket = (SSLServerSocket)  ctx.getServerSocketFactory().createServerSocket(9080);
        // 不需要认证客户端，就是只进行单向认证
        sslServerSocket.setNeedClientAuth(false);

        System.out.println("server start...");
        Socket s = sslServerSocket.accept();
        while (true) {
            try {
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                BufferedInputStream bis = new BufferedInputStream(input);
                BufferedOutputStream bos = new BufferedOutputStream(output);

                byte[] buffer = new byte[20];
                bis.read(buffer);
                System.out.println(new String(buffer));

                bos.write("Server Echo ".getBytes());
                bos.write(buffer);
                bos.flush();

            } catch (Exception e) {
                System.out.println(e);
                s.close();
            }
        }

    }

}