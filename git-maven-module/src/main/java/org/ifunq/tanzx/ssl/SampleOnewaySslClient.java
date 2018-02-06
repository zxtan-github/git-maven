package org.ifunq.tanzx.ssl;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Java SSL单向认证客户端
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-06 11:14
 **/
public class SampleOnewaySslClient {

    public static void main(String[] args) throws Exception {
        // 第一种写法
        // 将证书（公钥）导入进客户端的受信证书列表中
        System.setProperty("javax.net.ssl.trustStore", "D:/ifunq/支付申报/宝付/ssl/test_cacerts");
        // 可以直接使用服务器端一样的keystone，因为密匙对肯定是受信证书列表的
        // System.setProperty("javax.net.ssl.trustStore", "D:/ifunq/支付申报/宝付/ssl/test.keystore");
        System.setProperty("javax.net.debug", "ssl,handshake");
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 9080);

//        // 第二种写法
//        SSLContext ctx = SSLContext.getInstance("SSL");
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//        // 新建一个密钥库实例
//        KeyStore ks = KeyStore.getInstance("JKS");
//        // 输入密码打开密钥库
//        ks.load(new FileInputStream("D:/ifunq/支付申报/宝付/ssl/test_cacerts"), "123456".toCharArray());
//        // 不需要密码
//        tmf.init(ks);
//        // 初始化SSLContext上下文
//        ctx.init(null, tmf.getTrustManagers(), null);
//        // 创建服务端Socket
//        Socket s = (Socket)  ctx.getSocketFactory().createSocket("localhost", 9080);


        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg = "";
        while (!"exit".equals(msg)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            msg = input.readLine();
            writer.println(msg);
            writer.flush();
            System.out.println(reader.readLine());
        }
        s.close();

    }

}