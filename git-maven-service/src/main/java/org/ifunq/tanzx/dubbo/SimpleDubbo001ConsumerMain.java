package org.ifunq.tanzx.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.ifunq.tanzx.dubbo.service.DubboHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消费者类
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-30 10:54
 **/
public class SimpleDubbo001ConsumerMain {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo/SimpleDubbo001Consumer.xml");
        DubboHelloService dubboHelloService =
                (DubboHelloService) context.getBean("dubboHelloServiceConsumer");
        System.out.println(dubboHelloService.sayHello("tanzongxi"));

        // Dubbo泛化调用
        // 引用远程服务
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        // 弱类型接口名
        reference.setInterface("org.ifunq.tanzx.dubbo.service.DubboHelloService");
        // reference.setVersion("1.0.0");
        // 声明为泛化接口
        reference.setGeneric(true);

        ApplicationConfig applicationConfig = (ApplicationConfig) context.getBean("consumer001");
        reference.setApplication(applicationConfig);

        RegistryConfig registryConfig = context.getBean(RegistryConfig.class);
        reference.setRegistry(registryConfig);

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("sayHello", new String[] {"java.lang.String"},
                new Object[] {"tanchengyu"});
        System.out.println(result);

        // 用Map表示POJO参数，如果返回值为POJO也将自动转成Map
        Map<String, Object> person = new HashMap<String, Object>();
        person.put("name", "tanzongxi");
        person.put("age", 29);
        // 如果返回POJO将自动转成Map
        Object personResult = genericService.$invoke("setPerson",
                new String[] {"org.ifunq.tanzx.pojo.Person", "java.lang.String", "int"},
                new Object[] {person, "tangyan", 21});
        System.out.println(personResult);


        // 用Map表示POJO参数，如果返回值为POJO也将自动转成Map
        Map<String, Object> person1 = new HashMap<String, Object>();
        person1.put("name", "tanchengyu");
        person1.put("age", 3);
        Map<String, Object> person2 = new HashMap<String, Object>();
        person2.put("name", "tanruoxi");
        person2.put("age", 1);
        List personList = new ArrayList();
        personList.add(person1);
        personList.add(person2);
        // 如果返回POJO将自动转成Map
        Object personListResult = genericService.$invoke("setPersoListName",
                new String[] {"java.util.List", "java.lang.String"},
                new Object[] {personList, "tangyan"});
        System.out.println(personListResult);

        // System.in.read();
    }
}
