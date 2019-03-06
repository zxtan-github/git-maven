package org.ifunq.tanzx.spring.HandlerAdapter;

import org.ifunq.tanzx.spring.bean.SimpleSpringBean02;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
         return (handler instanceof SimpleSpringBean02);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleSpringBean02 simpleSpringBean02 = (SimpleSpringBean02) handler;
        String resultString = simpleSpringBean02.sayMsg(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(resultString);
        return modelAndView;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }
}
