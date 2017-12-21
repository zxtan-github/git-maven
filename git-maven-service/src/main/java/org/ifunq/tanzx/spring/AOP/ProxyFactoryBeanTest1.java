package org.ifunq.tanzx.spring.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-13 23:13
 **/
public class ProxyFactoryBeanTest1 {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/AOP/AopProxyFactoryBean1.xml");
        CarService carService = (CarService) ctx.getBean("carService");
        carService.start();
        carService.getLoadAmount();
        String driver = carService.setDriver("fruitking");
        System.out.println(driver);
        System.out.println("------------------------------");
        carService.loadGoods("Miss Mary");
        System.out.println("------------------------------");
        try{
            carService.loadGoods(null);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        System.out.println("------------------------------");
        try{
            carService.loadGoods("tiger");
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
