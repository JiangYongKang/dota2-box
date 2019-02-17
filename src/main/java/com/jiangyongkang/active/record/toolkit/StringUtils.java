package com.jiangyongkang.active.record.toolkit;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    /**
     * 下划线转驼峰
     *
     * @param name
     * @param lower
     * @return
     */
    public static String camelcase(String name, boolean lower) {
        String newName = Arrays.stream(name.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining());
        return lower ? newName.substring(0, 1).toLowerCase() + newName.substring(1) : newName;
    }

    /**
     * 驼峰转下划线
     *
     * @param name
     * @param lower
     * @return
     */
    public static String underscore(String name, boolean lower) {
        String newName = name.replaceAll("[A-Z]", "_$0");
        return lower ? newName.toLowerCase() : newName.toUpperCase();
    }

}
