package com.atguigu.springboot.common.util;
/*
 * Project: greenline-hrs-open-service-war
 * 
 * File Created at 2012-6-18
 * 
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @Type XmlConverter
 * @Desc
 * @author weirui.shenwr
 * @date 2012-6-18
 * @Version V1.0
 */
public class XmlConverter {


    /**
     * 对象转xml
     * 
     * @param t
     * @param classiz
     * @return
     */
    public static <T> String toXML(T t, Class<T> clazz) {
    	
    	
    	
    	Field[] fs=clazz.getDeclaredFields();
    	StringBuilder sb=new StringBuilder();
		for(Field field:fs){
			
			PropertyDescriptor pd = null;
			try {
				pd = new PropertyDescriptor(field.getName(), clazz);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			
			
			Method wM = pd.getReadMethod();
			try {
				Object o=wM.invoke(t);
				if(o!=null)
					sb.append("<"+field.getName()+">"+o+"</"+field.getName()+">");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><responseinfo>"+sb.toString()+"</responseinfo>";
    }
    
}
