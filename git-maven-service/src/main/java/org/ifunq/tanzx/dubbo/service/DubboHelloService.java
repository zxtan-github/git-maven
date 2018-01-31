package org.ifunq.tanzx.dubbo.service;/**
 * Created by tanzx on 2018/1/30.
 */

import org.ifunq.tanzx.pojo.Person;

import java.util.List;

/**
 * dubbo你好服务
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-30 9:52
 **/
public interface DubboHelloService {
    String sayHello(String name);
    Person setPerson(Person person, String name, int age);
    List<Person> setPersoListName(List<Person> personList, String name);
}