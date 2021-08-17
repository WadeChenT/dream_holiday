package com.hotel.holiday.dream.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getDate2GMTString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateConstants.GMTFORMAT, Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * 取得當前時間
     */
    public static LocalDateTime getNowLocal() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    /**
     * 取得當前時間
     */
    public static Date getNowDate() {
        return Date.from(getNowLocal().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 取得當前時間字串
     */
    public static String getNowString() {
        return getNowLocal().format(DateConstants.DATETIME_FORMAT_NO_SLASH);
    }

    /**
     * 取得當前時間字串
     */
    public static String getNowStringTomm() {
        return getNowLocal().format(DateConstants.YYYYMMddHHmm);
    }

    /**
     * 取得當前時間字串
     */
    public static String getNowStringDYM() {
        return getNowLocal().format(DateConstants.DD_MM_YY);
    }

    /**
     * 取得當前時間字串
     */
    public static String getNowStringMs() {
        return getNowLocal().format(DateConstants.YYYY_MM_DD_HH_mm_SS_MS);
    }


    public static class DateConstants {

        public static final String DMY = "dd/MM/yyyy";
        public static final String YMD = "yyyy-MM-dd";
        public static final String HMS = "HH:mm:ss";
        public static final String HM = "HH:mm";
        public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
        public static final String YMDHMS2 = "yyyy/MM/dd HH:mm:ss";
        public static final String YMDHMSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss.S";
        public static final String MDHM = "MM/dd HH:mm";
        public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
        public static final String YMDwithoutSeparator = "yyyyMMddHHmmss";
        public static final String GMTFORMAT = "EEE, dd MMM YYYY HH:mm:ss 'GMT ('Z')'";

        /**
         * 日期格式化成dd/mm/yy
         */
        public static final DateTimeFormatter DD_MM_YY = DateTimeFormatter.ofPattern(DMY);
        /**
         * 日期格式化成yyyy-MM-dd
         */
        public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(YMD);
        /**
         * 日期格式化成HH:mm:ss
         */
        public static final DateTimeFormatter HH_mm_SS = DateTimeFormatter.ofPattern(HMS);
        /**
         * 日期格式化成HH:mm
         */
        public static final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern(HM);
        /**
         * 日期格式化成yyyy-MM-dd HH:mm:ss
         */
        public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS = DateTimeFormatter.ofPattern(YMDHMS);
        /**
         * 日期格式化成yyyy/MM/dd HH:mm:ss，如2018/04/05 21:50:58
         */
        public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS2 = DateTimeFormatter.ofPattern(YMDHMS2);
        /**
         * 日期格式化成MM/dd HH:mm
         */
        public static final DateTimeFormatter MM_DD_HH_mm = DateTimeFormatter.ofPattern(MDHM);
        /**
         * 日期格式化成yyyy-MM-dd HH:mm:ss.SSS
         */
        public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS_MS = DateTimeFormatter.ofPattern(YMDHMSSSS);
        /**
         * 日期格式化成yyyy-MM-dd HH:mm:ss.S
         */
        public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS_S = DateTimeFormatter.ofPattern(YMDHMSS);
        /**
         * 日期格式化成yyyyyMMddHHmm
         */
        public static final DateTimeFormatter YYYYMMddHHmm = DateTimeFormatter.ofPattern(yyyyMMddHHmm);
        /**
         * 日期格式化成yyyyMMddHHmmss
         */
        public static final DateTimeFormatter DATETIME_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern(YMDwithoutSeparator);
        /**
         * 日期格式化成yyyyMMdd
         */
        public static final DateTimeFormatter DATE_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern("yyyyMMdd");
        /**
         * 日期格式化成HHmmss
         */
        public static final DateTimeFormatter TIME_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern("HHmmss");
        /**
         * 日期格式化成YYMM
         */
        public static final DateTimeFormatter YYMM = DateTimeFormatter.ofPattern("YYMM");
    }
}
