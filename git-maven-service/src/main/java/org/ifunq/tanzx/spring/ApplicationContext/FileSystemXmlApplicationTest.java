package org.ifunq.tanzx.spring.ApplicationContext;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.ifunq.tanzx.spring.bean.SpringBean002;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 测是
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-08 23:10
 **/
public class FileSystemXmlApplicationTest {

    public static void main(String[] args) {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("D:/development/IDEA/git-projects/git-maven/git-maven-service/src/main/resources/spring/SimpleXmlBean.xml");
        SpringBean001 springBean001 = (SpringBean001) fileSystemXmlApplicationContext.getBean("bean001");
        springBean001.sayHello();
        SpringBean002 springBean002 = (SpringBean002) fileSystemXmlApplicationContext.getBean("bean002");
        springBean002.sayHi();
    }

}