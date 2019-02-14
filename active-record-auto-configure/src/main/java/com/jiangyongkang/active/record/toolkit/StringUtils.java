package com.jiangyongkang.active.record.toolkit;

/**
 * author: vincent
 * date: 2019-02-13 11:52
 * comment:
 */

public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static String appendQuotes(Object object) {
        return "'" + object + "'";
    }

}
