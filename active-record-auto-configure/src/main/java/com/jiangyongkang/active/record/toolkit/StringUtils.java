package com.jiangyongkang.active.record.toolkit;

/**
 * author: vincent
 * date: 2019-02-13 11:52
 * comment:
 */

public class StringUtils {

    public static String captureName(String name) {
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
