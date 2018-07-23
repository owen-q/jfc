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
                break;
            case MONDAY:
                strDay = "월";
                break;
            case SATURDAY:
                strDay = "토";
                break;
        }

        return strDay;
    }
}
