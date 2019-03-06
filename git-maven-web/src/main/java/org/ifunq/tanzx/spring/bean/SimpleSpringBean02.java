package org.ifunq.tanzx.spring.bean;

import javax.servlet.http.HttpServletRequest;

public class SimpleSpringBean02 {
    public String sayMsg(HttpServletRequest request) {
        return "hello";
    }
}