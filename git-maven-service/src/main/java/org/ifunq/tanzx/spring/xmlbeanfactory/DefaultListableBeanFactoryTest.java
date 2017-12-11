package org.ifunq.tanzx.spring.xmlbeanfactory;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.ifunq.tanzx.spring.bean.SpringBean002;
import org.ifunq.tanzx.spring.bean.SpringBean003;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 3212
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-07 17:18
 **/
public class DefaultListableBeanFactoryTest {

    public static void main(String[] args) {
        // resource值相当于一个文件，定义BeanDefinition的文件
        ClassPathResource resource = new ClassPathResource("spring/SimpleXmlBean.xml");
        // 新建空的父类Bean工厂
        DefaultListableBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        // xml方式解析BeanDefinition定义文件，并设定要加载的容器
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(parentBeanFactory);
        // 把resource中定义的BeanDefinition加载进容器中，并返回加载BeanDefinition的个数
        int count = definitionReader.loadBeanDefinitions(resource);
        // BeanDefinition就是相当于bean的元信息，当第一次调用getBean时，会根据元信息生产bean
        // 同一个class只要定义了不同的bean名称，它的BeanDefinition也是不相同的，如bean002、bean002-1
        System.out.println("加载个数--->"+count+"  bean个数--->"+ parentBeanFactory.getBeanDefinitionCount());
        SpringBean001 springBean001 = (SpringBean001) parentBeanFactory.getBean("bean001");
        springBean001.sayHello();
        SpringBean002 springBean002 = (SpringBean002) parentBeanFactory.getBean("bean002");
        springBean002.sayHi();

        // 新建空的子类Bean工厂
        DefaultListableBeanFactory sonBeanFactory = new DefaultListableBeanFactory(parentBeanFactory);
        // xml方式解析bean定义文件，并设定要加载的容器
        definitionReader = new XmlBeanDefinitionReader(sonBeanFactory);
        // 直接根据文件地址读取xml文件，把BeanDefinition加载进容器中，并返回加载BeanDefinition的个数
        count = definitionReader.loadBeanDefinitions("spring/SimpleXmlBean2.xml");
        // BeanDefinition并不是bean没有实例化真正的bean，是lazy加载，所以不能检验bean的错误
        // 比如在SimpleXmlBean2.xml定义的SpringBean005并不存在，但是加载BeanDefinition是不会报错的，第一次getBean是才会报错
        // BeanFactory和ApplicationContext有一个很大的去表点就在于，ApplicationContext不是lazy加载，可以检验Bean错误，就是很多时候启动会报bean没找到等
        System.out.println("加载个数--->"+count+"  bean个数--->"+ sonBeanFactory.getBeanDefinitionCount());
        // 子类工厂的BeanDefinitionCount的是不通用的，就是子类工厂的有自己维护的beanDefinitionMap的，父类有父类的，子类工厂并不能继承父类工厂的
        // 获取父类工厂的bean时，是通过父类工厂的引用去父类工厂中获取的
        springBean001 = (SpringBean001) sonBeanFactory.getBean("bean001");

        SpringBean001 springBean00101 = new SpringBean001();
        SpringBean001 springBean00102 = new SpringBean001();
        if (springBean00101.getClass() == springBean00102.getClass()) {
            System.out.println("true");
        }
        if (springBean00101.getClass() == springBean001.getClass()) {
            System.out.println("true");
        }

        springBean001.sayHello();
        springBean002 = (SpringBean002) sonBeanFactory.getBean("bean002");
        springBean002.sayHi();
        // 获取自己的定义bean
        SpringBean003 springBean003 = (SpringBean003) sonBeanFactory.getBean("bean003");
        springBean003.sayNihao();
        SpringBean001 springBean004 = (SpringBean001) sonBeanFactory.getBean("bean004");
        springBean004.sayHello();

        // 孙类工厂的beanDefinitionMap为长度0，并没有继承父类的
        // getBeanDefinitionCount其实就是beanDefinitionMap.size()
        DefaultListableBeanFactory grandsonBeanFactory = new DefaultListableBeanFactory(sonBeanFactory);
        System.out.println("bean个数--->"+ grandsonBeanFactory.getBeanDefinitionCount());

        grandsonBeanFactory.getParentBeanFactory();

        // 获取一个定义不存在的bean类型，报错
        sonBeanFactory.getBean("bean005");


        // 运行结果
//        加载个数--->3  bean个数--->3
//        SpringBean001 sayHello...
//        SpringBean002 sayHi...
//        加载个数--->3  bean个数--->3
//        SpringBean001 sayHello...
//        SpringBean002 sayHi...
//        SpringBean001 sayNihao...
//        SpringBean001 sayHello...
//        bean个数--->3
//        Exception in thread "main" org.springframework.beans.factory.CannotLoadBeanClassException: Cannot find class [org.ifunq.tanzx.spring.bean.SpringBean005] for bean with name 'bean005' defined in class path resource [spring/SimpleXmlBean2.xml]; nested exception is java.lang.ClassNotFoundException: org.ifunq.tanzx.spring.bean.SpringBean005
//        at org.springframework.beans.factory.support.AbstractBeanFactory.resolveBeanClass(AbstractBeanFactory.java:1327)
//        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:452)
//        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:304)
//        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228)
//        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:300)
//        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195)
//        at org.ifunq.tanzx.spring.xmlbeanfactory.DefaultListableBeanFactoryTest.main(DefaultListableBeanFactoryTest.java:63)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:497)
//        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
//        Caused by: java.lang.ClassNotFoundException: org.ifunq.tanzx.spring.bean.SpringBean005
//        at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
//        at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
//        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
//        at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
//        at org.springframework.util.ClassUtils.forName(ClassUtils.java:247)
//        at org.springframework.beans.factory.support.AbstractBeanDefinition.resolveBeanClass(AbstractBeanDefinition.java:395)
//        at org.springframework.beans.factory.support.AbstractBeanFactory.doResolveBeanClass(AbstractBeanFactory.java:1348)
//        at org.springframework.beans.factory.support.AbstractBeanFactory.resolveBeanClass(AbstractBeanFactory.java:1319)
//        ... 11 more
    }
}
