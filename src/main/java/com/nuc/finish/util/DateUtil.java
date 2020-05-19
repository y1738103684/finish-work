package com.nuc.finish.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 16:38
 */
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date getBeforeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -day);
        Date time = calendar.getTime();
        return time;
    }

    public static String convertToFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }
}
