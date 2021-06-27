package com.luanvo.coincat.utils;

import java.util.Calendar;
import java.util.Locale;

public class ZDateUtils {


    public static final long WEEK_MILIS = 604800000l;
    public static final long MONTH_MILIS = 2419200000l;
    public static final long DAY_MILIS = 86400000l;

    public static long getTimeNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getTimeLastWeek() {
        return ZDateUtils.getTimeNow() - WEEK_MILIS;
    }


    public static long getStartOfMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance(Locale.ROOT);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String getDateStr(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
    }

    private static long getEndOfMonth(int month, int year) {
        return ZDateUtils.getStartOfMonth(month, year) - 1000;
    }

    public static void main(String[] args) {
        long date = Calendar.getInstance().getTimeInMillis();
        System.out.println(ZDateUtils.getDateStr(date));
    }
}
