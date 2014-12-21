package org.fruit.blueberry.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class DateUtil extends org.apache.commons.lang.time.DateUtils {
    private static final Log logger = LogFactory.getLog(DateUtil.class);

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_US = "MM/dd/yyyy";
    public static final String DATE_TIME_PATTERN_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static String formatDate(Date date) {
        return formatDate(date, DATE_PATTERN);
    }

    public static String formatUSDate(Date date) {
        return formatDate(date, DATE_PATTERN_US);
    }

    public static String formatTime(Date time) {
        return formatDate(time, TIME_PATTERN);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_PATTERN);
    }

    public static String formatDateTime(Date date, String pattern) {
        return formatDate(date, pattern);
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatCalendar(Calendar calendar, String format) {
        Date date = calendar.getTime();
        return formatDate(date, format);
    }

    public static Date parseDate(String date) {
        try {
            return parseDate(date, new String[]{DATE_PATTERN});
        } catch (ParseException e) {
            logger.error("Parse date failed. [" + date + "]");
            return null;
        }
    }

    public static Date parseDate(String date, String fmt) {
        try {
            return parseDate(date, new String[]{fmt});
        } catch (ParseException e) {
            logger.error("Parse date failed. [" + date + "]");
            return null;
        }
    }

    public static Date parseAllType(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }

        if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            return parseDate(date, DATE_PATTERN);
        } else if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            return parseDate(date, DATE_TIME_PATTERN);
        } else if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}")) {
            return parseDate(date, DATE_TIME_PATTERN_FULL);
        } else {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
                return dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static Calendar convert(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1;
    }

    public static Calendar parseCalendar(String date) {
        try {
            return convert(parseDate(date, new String[]{DATE_PATTERN}));
        } catch (ParseException e) {
            logger.error("Parse calendar failed. [" + date + "]");
            return null;
        }
    }

    public static Calendar parseCalendarTime(String date) {
        try {
            return convert(parseDate(date, new String[]{DATE_TIME_PATTERN}));
        } catch (ParseException e) {
            logger.error("Parse calendar time failed. [" + date + "]");
            return null;
        }
    }

    public static String yesterday() {
        Calendar now = Calendar.getInstance();
        Calendar yesterday = addDays(now, -1);
        return formatCalendar(yesterday, DATE_PATTERN);
    }

    public static String today() {
        Calendar now = Calendar.getInstance();
        return formatCalendar(now, DATE_PATTERN);
    }

    public static String getAheadStartDays(int days) {
        Calendar now = Calendar.getInstance();
        Calendar ahead = addDays(now, -days);
        return getStartOfDay(ahead.getTime(), DATE_PATTERN);
    }

    public static String getAheadEndDays(int days) {
        Calendar now = Calendar.getInstance();
        Calendar ahead = addDays(now, -days);
        return getEndOfDate(ahead.getTime());
    }

    public static String getAheadDays(int days) {
        Calendar now = Calendar.getInstance();
        Calendar ahead = addDays(now, -days);
        return formatDate(ahead.getTime());
    }

    public static String getStartOfDay(Date date, String fmt) {
        String strDate = formatDate(date, fmt);
        if (StringUtils.isNotEmpty(strDate)) {
            return strDate + " 00:00:00";
        } else {
            return null;
        }
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static String getEndOfDate(Date date) {
        String strDate = formatDate(date, DATE_PATTERN);
        if (StringUtils.isNotEmpty(strDate)) {
            return strDate + " 23:59:59.999";
        } else {
            return null;
        }
    }

    public static String getEndOfDate(Date date, String fmt) {
        return formatDate(date, fmt) + " 23:59:59";
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static Date getStartOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
        return dateTime.toDate();
    }

    public static Date getEndOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
        dateTime = dateTime.plusMonths(1).minusMillis(1);
        return dateTime.toDate();
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear() {
        return getYear(new Date());
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static List<Date> getDatesBetweenTwoDate(Date startDate, Date endDate) {
        List lDate = new ArrayList();
        lDate.add(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        while (true) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);
        return lDate;
    }

    public static int getCurrentDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getDateString(int offsetDaysFromNow) {
        return formatDate(offsetDays(new Date(), offsetDaysFromNow));
    }

    public static Date offsetDays(Date date, int days) {
        return date == null ? date : new DateTime(date.getTime()).plusDays(days).toDate();
    }

    public static Date offsetHours(Date date, int hours) {
        return date == null ? date : new DateTime(date.getTime()).plusHours(hours).toDate();
    }

    public static Date offsetDays(int days) {
        return offsetDays(new Date(), days);
    }

    public static Date lastMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (month == 0) {
            year--;
            month = (month - 1) + 12;
        } else {
            month--;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date lastQuarterDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (month <= 2) {
            year--;
            month = (month - 3) + 12;
        } else {
            month -= 3;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static int intervalDays(Date date1, Date date2) {
        return (int) (Math.abs(date1.getTime() - date2.getTime()) / MILLIS_PER_DAY);
    }

    public static Long intervalMinutes(Date date1, Date date2) {
        return Math.abs(date1.getTime() - date2.getTime()) / MILLIS_PER_MINUTE;
    }

    public static int intervalDays(Calendar date1, Calendar date2) {
        return intervalDays(date1, date2, false);
    }

    public static int intervalDays(Calendar date1, Calendar date2, boolean includeLastDay) {
        Calendar d1;
        Calendar d2;

        if (date1.before(date2)) {
            d1 = (Calendar) date1.clone();
            d2 = (Calendar) date2.clone();
        } else {
            d1 = (Calendar) date2.clone();
            d2 = (Calendar) date1.clone();
        }

        long start = org.apache.commons.lang.time.DateUtils.truncate(d1.getTime(), Calendar.DATE).getTime();
        long end = org.apache.commons.lang.time.DateUtils.truncate(d2.getTime(), Calendar.DATE).getTime();
        int days = (int) ((end - start) / MILLIS_PER_DAY);
        return includeLastDay ? days + 1 : days;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (intervalDays(date1, date2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameWeek(Date date1, Date date2, int dayOfWeek) {
        //My way
        /*Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if(c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR)){
            return true;
        }else {
            return false;
        }*/

        int intervalDays = intervalDays(date2, date1);

        if (intervalDays >= 7) {
            return false;
        } else {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            int day2OfWeek = calendar2.get(Calendar.DAY_OF_WEEK);
            int interval2Days = ((day2OfWeek + 7) - dayOfWeek) % 7;
            Date delimeterDate = addDays(date2, -interval2Days);
            if (intervalDays(date1, delimeterDate) >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isSameMonth(Date date1, Date date2, int dayOfMonth) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        int day2OfMonth = calendar2.get(Calendar.DAY_OF_MONTH);
        int interval2Days = 0;
        if (day2OfMonth >= dayOfMonth) {
            if ((calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
                    && (calendar1.get(Calendar.DAY_OF_MONTH) >= dayOfMonth)) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
                    || (calendar1.get(Calendar.DAY_OF_MONTH) >= dayOfMonth)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isSameQuarter(Date date1, Date date2) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        if ((calendar2.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR))
                && ((calendar2.get(Calendar.MONTH)) / 3 == (calendar1.get(Calendar.MONTH)) / 3)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFirstOfQuarter(Date date) {
        String dateStr = formatDate(date);
        if (dateStr.endsWith("01-01") || dateStr.endsWith("04-01")
                || dateStr.endsWith("07-01") || dateStr.endsWith("10-01")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEarlyThanYesterday(Date date) {
        String dateStr = formatDate(date, DATE_PATTERN);
        String yesterdayStr = yesterday();
        if (yesterdayStr.compareTo(dateStr) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static XMLGregorianCalendar create(int year, int month, int day) {
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(
                            new GregorianCalendar(year, month - 1, day));
        } catch (DatatypeConfigurationException e) {
            logger.error("Parse date failed. [" + year + "/" + month + "/" + day + "]");
        }
        return date;
    }

    public static Date createDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, date);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Calendar createCalendar(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, date);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar addDays(Calendar c, int amount) {
        Calendar cc = (Calendar) c.clone();
        cc.add(Calendar.DAY_OF_MONTH, amount);
        return cc;
    }

    public static Date addDays(Date d, int amount) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        return addDays(c, amount).getTime();
    }

    public static Date addHours(Date date, int hours) {
        return new DateTime(date.getTime()).plusHours(hours).toDate();
    }

    public static boolean after(Calendar c1, Calendar c2) {
        return truncate(c1, Calendar.DATE).after(truncate(c2, Calendar.DATE));
    }

    public static boolean beforeOrEqual(Calendar c1, Calendar c2) {
        return !truncate(c1, Calendar.DATE).after(truncate(c2, Calendar.DATE));
    }

    public static boolean afterOrEqual(Calendar c1, Calendar c2) {
        return !truncate(c1, Calendar.DATE).before(truncate(c2, Calendar.DATE));
    }

    public static boolean between(Calendar c, Calendar from, Calendar to) {
        return afterOrEqual(c, from) && beforeOrEqual(c, to);
    }

    public static XMLGregorianCalendar toXML(Date date) {
        if (date == null) {
            return null;
        }

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            int year = calendar.get(Calendar.ERA) == GregorianCalendar.BC ?
                    -calendar.get(Calendar.YEAR) : calendar.get(Calendar.YEAR);
            return datatypeFactory.newXMLGregorianCalendar(
                    year,
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    calendar.get(Calendar.MILLISECOND),
                    calendar.get((Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / 60000);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getLastMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * -1);
        Date monday = currentDate.getTime();
        return monday;
    }

    public static Date getLastMonthStartDate() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.MONTH, -1);
        return lastDate.getTime();
    }

    public static Date getLastMonthEndDate() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);
        lastDate.set(Calendar.DATE, 1);
        lastDate.roll(Calendar.DATE, -1);
        return lastDate.getTime();
    }

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static String getCurrentWeekday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String fistDayOfMonth = df.format(calendar.getTime());
        return fistDayOfMonth;
    }

    public static String getFirstDayOfMonth(DateFormat df) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String fistDayOfMonth = df.format(calendar.getTime());
        return fistDayOfMonth;
    }

    public static String getFirstDayOfWeek() {
        Calendar monday = Calendar.getInstance();
        Calendar result = getADayOfWeek(monday, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String fistDayOfWeek = df.format(result.getTime());
        return fistDayOfWeek;
    }

    private static Calendar getADayOfWeek(Calendar day, int dayOfWeek) {
        int week = day.get(Calendar.DAY_OF_WEEK);
        if (week == dayOfWeek)
            return day;
        int diffDay = dayOfWeek - week;
        if (week == Calendar.SUNDAY) {
            diffDay -= 7;
        } else if (dayOfWeek == Calendar.SUNDAY) {
            diffDay += 7;
        }
        day.add(Calendar.DATE, diffDay);
        return day;
    }

    public static Date getUtcDatetime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() - java.util.TimeZone.getDefault().getRawOffset());

        return cal.getTime();
    }

    public static String getLogTimestamp() {
        return formatDateTime(getUtcDatetime(), DATE_TIME_PATTERN_FULL);
    }

    public static Date fromXML(XMLGregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null) {
            return null;
        }

        Calendar calendar = gregorianCalendar.toGregorianCalendar();
        return calendar.getTime();
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar result = null;
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Date max(Date date1, Date date2) {
        return date1.after(date2) ? date1 : date2;
    }

    public static Date min(Date date1, Date date2) {
        return date1.before(date2) ? date1 : date2;
    }
}
