package com.tianbin.theoldreaderapp.common.util;

import java.util.Calendar;
import java.util.Locale;

/**
 * CalendarUtil
 * Created by tianbin on 16/11/30.
 */
public class CalendarUtil {


    public static String getBlogPostTime(long time) {
        time *= 1000;

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTimeInMillis(time);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(System.currentTimeMillis());

        // check year
        int apartYears = currentCalendar.get(Calendar.YEAR) - timeCalendar.get(Calendar.YEAR);
        if (apartYears >= 1) {
            return String.format(Locale.getDefault(),
                    "%04d/%02d/%02d %02d:%02d ",
                    timeCalendar.get(Calendar.YEAR),
                    timeCalendar.get(Calendar.MONTH) + 1,
                    timeCalendar.get(Calendar.DAY_OF_MONTH),
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE));
        }

        // check hours
        int apartHours = (int) ((currentCalendar.getTimeInMillis() - timeCalendar.getTimeInMillis()) / 1000 / 60 / 60);
        if (apartHours >= 24) {
            return String.format(Locale.getDefault(),
                    "%02d/%02d %02d:%02d ",
                    timeCalendar.get(Calendar.MONTH) + 1,
                    timeCalendar.get(Calendar.DAY_OF_MONTH),
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE));
        }

        if (apartHours >= 1) {
            return String.format("%s 小时前", apartHours);
        }

        int apartMinutes = (int) ((currentCalendar.getTimeInMillis() - timeCalendar.getTimeInMillis()) / 1000 / 60);
        if (apartMinutes >= 5) {
            return String.format("%s 分钟前", apartMinutes);
        }

        return "刚刚";
    }
}
