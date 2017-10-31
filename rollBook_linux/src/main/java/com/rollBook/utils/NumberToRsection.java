package com.rollBook.utils;

/**
 * Created by 周太宇 on 2017/9/28.
 */
public class NumberToRsection {
    /**
     * 将传来的数字转换为上课节数的字符串
     *
     * @param num
     * @return
     */
    public static String toRsectionString(int num) {
        if (num == 1) {
            return "1-2节";
        }
        if (num == 2) {
            return "2-4节";
        }
        if (num == 3) {
            return "5-6节";
        }
        if (num == 4) {
            return "7-8节";
        }
        if (num == 5) {
            return "9-10节";
        } else
            return null;
    }

    /**
     * 将数据库的星期数字转换为字符串
     * @param num
     * @return
     */
    public static String todayString(int num) {
        switch (num) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期天";
            default:
                return null;
        }
    }
}
