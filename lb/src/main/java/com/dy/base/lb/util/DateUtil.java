package com.dy.base.lb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final int DAY_SECONDS = 24 * 3600;

    public static final int HOUR_SECONDS = 3600;

    public static final int MINUTE_SECONDS = 60;

    public static final String DATE_STRING_FORMAT_DAY3 = "yyyyMMdd";
    public static final String DATE_STRING_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STRING_FORMAT_NONE_SEPARATOR = "yyyyMMddHHmmss";
    public static final String HOUR_MINITE = "HH:mm";
    public static final String YEAR_MONTH_DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String YEAR = "yyyy";
    public static final String MONTH = "MM";
    public static final String DAY = "dd";
    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

    private static Date SET_NOW = new Date();

    private static Boolean ALLOW_SET_NOW = true;
    private static Boolean HAS_GET_NOW_ONCE = false;

    private DateUtil(){}

    /***
     *
     * @param date 时间
     * @return  cron类型的日期
     */
    public static String getCron(final Date  date){
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    /***
     *
     * @param cron Quartz cron的类型的日期
     * @return  Date日期
     */

    public static Date getDate(final String cron) {
        if(cron == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        try {
            return sdf.parse(cron);
        } catch (ParseException e) {
            return null;
        }
    }


    public static Date getSimpleDateDate(final String cron) {
        if(cron == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        try {
            return sdf.parse(cron);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        return formatDate(date, YEAR_MONTH_DATE);
    }

    public static String formatDate(Date date, String reg) {
        if (date != null) {
            return new SimpleDateFormat(reg).format(date);
        }
        else {
            return null;
        }
    }

    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DATE);
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            LOGGER.error("日期转换发生异常：", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static int minuteBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        Long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        Long time2 = cal.getTimeInMillis();
        Long betweenMins = (time2 - time1) / (1000 * 60);
        return betweenMins.intValue();
    }

    public static Date addSecends(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.SECOND, n);
        return calendar.getTime();
    }


    public static Date getBeginTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 499);
        return cal.getTime();
    }

    /**
     * 获取特定日期23：59：59
     *
     * @return 特定日期23：59：59
     * @throws
     */
    public static Date getEndTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 499);
        return cal.getTime();
    }

    public static int compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    public static String getTodayStr(String format) {
        Long date = System.currentTimeMillis();
        return DateUtil.convertDateLongToDateString(format, date);
    }

    public static String getNowTimeStr(String format) {
        return formatDate(new Date(), format);
    }

    public static String convertDateLongToDateString(String format, Long time) {
        if (time == null || time == 0) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        Timestamp now = new Timestamp(time);
        return df.format(now);
    }

    public static Date parseDate(String date, String reg) {
        DateFormat dateFormat = new SimpleDateFormat(reg);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getNOW() {
        if(HAS_GET_NOW_ONCE){
            HAS_GET_NOW_ONCE = false;
            return (Date)SET_NOW.clone();
        }else{
            return new Date();
        }
    }


    public static Date getMonthBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getMonthEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date getYearBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH,0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getYearEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH,11);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }


}
