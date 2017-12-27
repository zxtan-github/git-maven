package org.ifunq.tanzx.spring.controller;

import org.ifunq.tanzx.spring.view.ExcelView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ExcelView
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-26 19:00
 **/
public class ExcelViewController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExcelView viewExcel = new ExcelView();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(viewExcel);
        return modelAndView;
    }
}