package org.ifunq.tanzx.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Dubbo服务启动
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-29 18:18
 **/
public class SimpleDubbo001Provider001Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/SimpleDubbo001Provider.xml");
        context.start();
        System.out.println("服务已经启动...");
        System.in.read();

        // com.alibaba.dubbo.container.Main.main(args);
    }
}