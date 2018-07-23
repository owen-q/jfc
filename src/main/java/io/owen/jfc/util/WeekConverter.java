package io.owen.jfc.util;

import java.time.DayOfWeek;

/**
 * Created by owen_q on 2018. 7. 23..
 */
public enum WeekConverter {
    ;

    public static String convert(DayOfWeek dayOfWeek){
        String strDay = "";

        switch(dayOfWeek){
            case SUNDAY:
                strDay = "일";
            case MONDAY:
                strDay = "월";
            case SATURDAY:
                strDay = "토";
        }

        return strDay;
    }
}
