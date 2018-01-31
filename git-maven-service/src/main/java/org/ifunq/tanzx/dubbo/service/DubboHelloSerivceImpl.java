package org.ifunq.tanzx.dubbo.service;

import org.ifunq.tanzx.pojo.Person;

import java.util.List;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-01-30 9:53
 **/
public class DubboHelloSerivceImpl implements DubboHelloService {
    public String sayHello(String name) {
        return "Hello World! " + name;
    }

    public Person setPerson(Person person, String name, int age) {
        System.out.println(person);
        person.setName(name);
        person.setAge(age);
        return person;
    }

    public List<Person> setPersoListName(List<Person> personList, String name) {
        for (Person person : personList) {
            System.out.println(person);
            person.setName(name);
        }
        return personList;
    }
}