package com.rollBook.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 周太宇 on 2017/9/24.
 */
public class Test {
    public static void main(String args[]) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date now =new Date();
        //得到系统年份
        Calendar a=Calendar.getInstance();
        int year=a.get(Calendar.YEAR);
        //定义夏季学期的开始和结束
        String summerBegin=year+"-03-01";
        String summerEnd=year+"-08-20";
        Date date1 = sdf.parse( summerBegin );
        Date date2 = sdf.parse( summerEnd );
        System.out.printf(date1.toString());
        //定义冬季学期的开始与结束
        String winterBegin="";
        String winterEnd="";
    }
}
