package org.ifunq.tanzx.dubbo;

import org.ifunq.tanzx.dubbo.service.DubboHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 消费者类
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-30 10:54
 **/
public class SimpleDubbo002ConsumerMain {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/SimpleDubbo002Consumer.xml");
        DubboHelloService dubboHelloService = (DubboHelloService) context.getBean("dubboHelloServiceConsumer");
        System.out.println(dubboHelloService.sayHello("tanzongxi"));
        System.in.read();
    }
}