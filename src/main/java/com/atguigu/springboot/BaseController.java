package com.atguigu.springboot;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    /**Session Attribute**/
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name){
        Object object=request.getSession().getAttribute(name);
        return object==null?null:(T) object;
    }

    public String getParameter(String name){
        return request.getParameter(name);
    }
    public String[] getParameterValues(String name){
        return request.getParameterValues(name);
    }
}
