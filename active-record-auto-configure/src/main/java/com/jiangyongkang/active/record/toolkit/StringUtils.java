package com.jiangyongkang.active.record.toolkit;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: vincent
 * date: 2019-02-13 11:52
 * comment:
 */

public class StringUtils {

    public static String captureName(String name) {
        return Stream.of(name.split("_")).map(subName -> {
            char[] chars = subName.toCharArray();
            chars[0] -= 32;
            return String.valueOf(chars);
        }).collect(Collectors.joining());
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
