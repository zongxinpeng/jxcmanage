package com.unionpaysmart.odin.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date Util
 *
 * @author Kevin Huang
 * @version 2016年7月8日 上午11:38:10
 */
public class DateUtils {
    public final static String YYYYMM = "yyyy-MM";
    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String YYYYMMDDHHMMSSsss = "yyyyMMddHHmmssSSS";

    public final static String YYYY_MM_DD = "yyyyMMdd";
    public final static String HH_MM_SS = "HHmmss";
    public final static String YYYYMMDDHHmm = "yyyyMMddHHmm";

    public final static int DAY_HOUR = 24;
    public final static int DAY_MINUTE = 24 * 60;
    public final static int DAY_SECONDS = 24 * 60 * 60;
    public final static int DAY_TIME = 24 * 60 * 60 * 1000;

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    public static String getNowTimeStr() {
        return parse(new Date(), YYYYMMDDHHmmss);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (null == dateFormat) {
            synchronized (object) {
                if (null == dateFormat) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }


    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        return StringToDate(date, YYYYMMDD);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (null != date) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static boolean isDate(String date, String pattern) {
        boolean result = false;
        if (null != date) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
                format.setLenient(false);
                format.parse(date);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (null != date) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date        旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    /**
     * 得到时间的long形式
     *
     * @return long
     */
    public long getLongTime() {
        return new Date().getTime();
    }

    /**
     * 得到时间的long形式
     *
     * @return long
     */
    public static long getTimeStamp() {
        return new Date().getTime();
    }


    /**
     * 获取当天开始间戳
     *
     * @return long
     */
    public static long getDayStartLongTime() {
        return getDayStartLongTime(new Date());
    }

    /**
     * 获取某天开始间戳
     *
     * @return long
     */
    public static long getDayStartLongTime(Date date) {
        String nowday = DateUtils.DateToString(date, YYYY_MM_DD) + "000000";
        return DateUtils.StringToDate(nowday, YYYYMMDDHHmmss).getTime();
    }

    /**
     * 获取时当天结束间戳
     *
     * @return long
     */
    public static long getDayendLongTime() {
        String nowday = DateUtils.DateToString(new Date(), YYYY_MM_DD) + "235959";
        return DateUtils.StringToDate(nowday, YYYYMMDDHHmmss).getTime();
    }

    /**
     * 得到当前日期的字符串表现形式
     *
     * @return String
     */
    public static String getDateStr() {
        return parse(new Date(), YYYYMMDD);
    }

    /**
     * 得到当前日期和时间的字符串表示形式
     *
     * @return String
     */
    public static String getDateTimeStr() {
        return parse(new Date(), YYYYMMDDHHMMSS);
    }

    /**
     * 根据传入的Date和转换格式对日期进行格式化，并返回字符串表式形式
     *
     * @param date   date
     * @param format format
     * @return String
     */
    public static String parse(Date date, String format) {
        if (date == null) {
            return "";
        }

        if (format == null || format.equals("")) {
            format = YYYYMMDD;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date).toString();
        } catch (RuntimeException ex) {

            return "";
        }
    }

    /**
     * Convert date to DateUtils.YYYYMMDDHHMMSSSSS
     *
     * @param dateStr 检测时间字符串
     * @return dateTime
     */
    public static String checkDateStr(String dateStr) {
        int len = dateStr.length();
        int index = dateStr.indexOf(".");
        String date = null;

        if (index >= 0) {
            int wlen = index + 1;
            if (len - wlen >= 3) {
                date = dateStr.substring(0, wlen + 3);
            } else {
                StringBuilder sb = new StringBuilder(dateStr);
                for (int i = 0; i < 3 + wlen - len; i++) {
                    sb.append("0");
                }
                date = sb.toString();
            }
        } else {
            StringBuilder sb = new StringBuilder(dateStr).append(".000");
            date = sb.toString();
        }

        return date;
    }

    public static Date parseDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        Date date = null;
        try {
            date = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

	public static String diff(Date start, Date end) {
		long cost = end.getTime() - start.getTime();
		return cost + Constant.EMPTY;
	}

    /**
     * 获取当前日期day天后的日期
     *
     * @param day day
     * @return Date
     */
    public static Date addDay(int day) {
        return addDay(new Date(), day);
    }

    /**
     * 日期加减
     *
     * @param date date
     * @param day  day
     * @return 返回加day天后的日期
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取当前日期 month 月后的日期
     *
     * @param month day
     * @return Date
     */
    public static Date addMonth(int month) {
        return addMonth(new Date(), month);
    }

    /**
     * 日期加减
     *
     * @param date  date
     * @param month month
     * @return 返回加 month 天后的日期
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 日期相减
     *
     * @param date
     * @return 返回 date 距今的月份数
     */
    public static String getDiffMonth(String date) {
        if (StringUtils.isBlank(date)) {
            return date;
        }
        Date startDate = StringToDate(date, YYYYMM);
        Date endDate = new Date();
        Integer diffNum = getDifMonth(startDate, endDate);

        return Integer.toString(diffNum);
    }

    /**
     * 日期相减
     *
     * @param date YYYY_MM_DD
     * @return 返回 date 距今的天数
     */
    public static String getDiffDay(String date, String patten) {
        if (StringUtils.isBlank(date)) {
            return date;
        }
        Date startDate = StringToDate(date, patten);
        Date endDate = new Date();
        Integer diffNum = getDifDay(startDate, endDate);

        return Integer.toString(diffNum);
    }

    /**
     * 日期相减
     *
     * @param beginDate
     * @param endDate
     * @return 入参日期相差的月份数，任意入参为空则返回 null
     */
    public static String getDiffMonth(String beginDate, String endDate) {
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return null;
        }
        Date start = StringToDate(beginDate, YYYYMM);
        Date end = StringToDate(endDate, YYYYMM);
        Integer diffNum = getDifMonth(start, end);

        return Integer.toString(diffNum);
    }

    /**
     * 日期相减
     *
     * @param startDate
     * @param endDate
     * @return 返回两个日期相差的月份数
     */
    public static Integer getDifMonth(Date startDate, Date endDate){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 日期相减
     *
     * @param startDate
     * @param endDate
     * @return 返回两个日期相差的天数
     */
    public static Integer getDifDay(Date startDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 == year2) {
            return day2 - day1;
        } else {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        }
    }

    /**
     * 获取时间段内的每一天
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getDays(String beginDate, String endDate){
        return getDays(StringToDate(beginDate), StringToDate(endDate));
    }

    /**
     * 获取时间段内的每一天
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getDays(Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        List<String> daysStrList = new ArrayList<String>();
        daysStrList.add(sdf.format(beginDate));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(beginDate);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);
        while (endDate.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String dayStr = sdf.format(calBegin.getTime());
            daysStrList.add(dayStr);
        }
        return daysStrList;
    }
}
