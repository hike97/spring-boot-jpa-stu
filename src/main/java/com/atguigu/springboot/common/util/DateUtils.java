package com.atguigu.springboot.common.util;


/**
 * Project: guahao-portal-web-home
 * 
 * File Created at 2012-9-26
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Type DateUtils
 * @Desc 日期公共方法类
 * @author jianyun.zheng
 * @date 2012-9-26
 * @Version V1.0
 */
public class DateUtils {

    private static final String DATE_FORMAT_YMD = "yyyy年MM月dd日";
    private static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";

    /**
     * 获取日期，格式为yyyy年MM月dd日
     * 
     * @author wangbiao
     * @param d
     * @return String
     */
    public static String getCurrentDateYmd(Date d) {
        SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMD);
        return destsmf.format(d);
    }

    /**
     * 用yyyy-MM-dd hh:mm:sss 来格式化日期
     * 
     * @author qinying
     * @param d
     * @return String
     */
    public static String format(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
        return destsmf.format(d);
    }

    /**
     * 用yyyy-MM-dd hh:mm:sss 来格式化日期
     * 
     * @author qinying
     * @param d
     * @return String
     */
    public static String formatYmdHm(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHM);
        return destsmf.format(d);
    }

    /**
     * 用yyyy-MM-dd hh:mm:sss 来格式化日期
     * 
     * @author qinying
     * @param d
     * @return String
     */
    public static String formatYYMMDD(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YYMMDD);
        return destsmf.format(d);
    }
    
    /**
     * 在当前年份基础上，增加年份
     * 
     * @author wangbiao
     * @param amount
     * @return Date
     */
    public static Date addYear(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 在当前月份基础上增加月份
     * 
     * @author wangbiao
     * @param amount
     * @return Date
     */
    public static Date addMonth(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 在当前日基础增加天数
     * 
     * @author wangbiao
     * @param amount
     * @return Date
     */
    public static Date addDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
    
    /**
     * 在给定的时间上加指定的天数
     * 
     * @param startDate
     * @param diff
     * @return
     */
    public static Date addDay(Date startDate, int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, diff);
        return calendar.getTime();
    }
    
    /**
     * 在给定的时间上加指定的月数
     * 
     * @param startDate
     * @param diff
     * @return
     */
    public static Date addDayWithStartDate(Date startDate, int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, diff);
        return calendar.getTime();
    }
    
    /**
     * 在给定的时间上加指定的月数
     * 
     * @param startDate
     * @param diff
     * @return
     */
    public static Date addMonthWithStartDate(Date startDate, int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, diff);
        return calendar.getTime();
    }
    
    /**
     * 在给定的时间上加指定的年
     * 
     * @param startDate
     * @param amount
     * @return
     */
    public static Date addYearWithStartDate(Date startDate, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null) {
        	calendar.setTime(startDate);
        }
        
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 在当前小时基础增加小时
     * 
     * @author wangbiao
     * @param amount
     * @return Date
     */
    public static Date addHour(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        return calendar.getTime();
    }

    /**
     * 字符串转换为日期
     */
    public static Date getStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(date);
    }

    /**
     * 用yyyyMMdd来格式化日期字符串
     * 
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(s);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * 用yyyy-MM-dd来格式化日期字符串
     * 
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddConnectDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 计算两个日期的差距(天数)，去掉时分秒
     * 
     * @param date1
     * @param date2
     * @return Long
     */
    public static Long getDays(Date date1, Date date2) {
        Long d1 = getYmdTime(date1).getTime() / 1000;
        Long d2 = getYmdTime(date2).getTime() / 1000;
        return Math.abs((d2 - d1)) / (24 * 3600);
    }

    /**
     * 去掉时分秒
     * 
     * @param date
     * @return Date
     */
    public static Date getYmdTime(Date date) {
        if (date == null) {
            return (new Date());
        }
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date convertTime = day.getTime();
        return convertTime;
    }
    
    /**
     * 用yyyy-MM-dd hh:mm:sss 来格式化日期
     * 
     * @author qinying
     * @param d
     * @return String
     */
    public static String dateformat(Date d,String fomart) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat destsmf = new SimpleDateFormat(fomart);
        return destsmf.format(d);
    }

}
