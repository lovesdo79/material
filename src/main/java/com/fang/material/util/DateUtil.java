package com.fang.material.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bgfang on 2017/4/21.
 */
public class DateUtil {
    private final static String DEFAULT_FORMAT = "yyyyMMddHHmmss";

    /**
     * 获取时间
     *
     * @param format 时间格式化方法
     * @return 字符串型时间
     */
    public static String getStringDate(String format) {
        String dateFormat = DEFAULT_FORMAT;
        if (!StringUtils.isEmpty(format)) {
            dateFormat = format;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得long型时间字符串
     *
     * @return
     */
    public static String getLongDateString() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }

    /**
     * 修改时间值
     *
     * @param date    时间
     * @param years   修改 年数
     * @param months  修改 月份
     * @param days    修改 天数
     * @param hours   修改 小时数
     * @param minutes 修改 分钟
     * @param seconds 修改 秒
     * @return 时间
     */
    public static Date getDate(Date date, int years, int months, int days, int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        calendar.add(Calendar.MONTH, months);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        calendar.add(Calendar.SECOND, seconds);

        date = calendar.getTime();
        return date;
    }

    public static Date getDate(Date date, int years, int months, int days, int hours, int minutes) {
        return getDate(date, years, months, days, hours, minutes, 0);
    }

    public static Date getDate(Date date, int years, int months, int days, int hours) {
        return getDate(date, years, months, days, hours, 0, 0);
    }

    public static Date getDate(Date date, int years, int months, int days) {
        return getDate(date, years, months, days, 0, 0, 0);
    }

    public static Date getDate(Date date, int years, int months) {
        return getDate(date, years, months, 0, 0, 0, 0);
    }

    public static Date getDate(Date date, int years) {
        return getDate(date, years, 0, 0, 0, 0, 0);
    }

}
