package org.ifunq.tanzx.spring.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-09 16:53
 **/
public class RepacerKoniqiwa implements MethodReplacer {
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println(" RepacerKoniqiwa sayKoniqiwa...");
        return null;
    }
}