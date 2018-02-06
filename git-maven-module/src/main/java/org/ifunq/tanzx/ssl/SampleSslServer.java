package org.ifunq.tanzx.ssl;/**
 * Created by tanzx on 2018/2/5.
 */

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;

/**
 * 简单ssl服务器
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-05 15:16
 **/
public class SampleSslServer {

    private static final int    DEFAULT_PORT                    = 7777;

    private static final String SERVER_KEY_STORE_PASSWORD       = "123456";
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = "123456";

    private SSLServerSocket     serverSocket;

    /**
     * 启动程序
     *
     * @param args
     */
    public static void main(String[] args) {
        SampleSslServer server = new SampleSslServer();
        server.init();
        server.start();
    }

    /**
     * <ul>
     * <li>听SSL Server Socket</li>
     * <li> 由于该程序不是演示Socket监听，所以简单采用单线程形式，并且仅仅接受客户端的消息，并且返回客户端指定消息</li>
     * </ul>
     */
    public void start() {
        if (serverSocket == null) {
            System.out.println("ERROR");
            return;
        }
        while (true) {
            try {
                Socket s = serverSocket.accept();
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                BufferedInputStream bis = new BufferedInputStream(input);
                BufferedOutputStream bos = new BufferedOutputStream(output);

                byte[] buffer = new byte[20];
                bis.read(buffer);
                System.out.println(new String(buffer));

                bos.write("Server Echo".getBytes());
                bos.flush();

                s.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * <ul>
     * <li>ssl连接的重点:</li>
     * <li>初始化SSLServerSocket</li>
     * <li>导入服务端私钥KeyStore，导入服务端受信任的KeyStore(客户端的证书)</li>
     * </ul>
     */
    public void init() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");

            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream("E://kserver.keystore"), SERVER_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream("E://tserver.keystore"), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
            tmf.init(tks);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(DEFAULT_PORT);
            serverSocket.setNeedClientAuth(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}