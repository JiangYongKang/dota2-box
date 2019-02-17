package com.jiangyongkang.active.record.toolkit;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * author: vincent
 * date: 2019-02-17 00:59
 * comment:
 */

public class SQLUtils {

    public static String tableName(Class<?> moduleClass) {
        return moduleClass.getSimpleName().toLowerCase();
    }

    public static String createSqlStatement(String tableName, Set<String> attributes) {
        return "insert into " + tableName + " (" + String.join(", ", attributes) + ") values (" +
                attributes.stream().map(attribute -> "?").collect(Collectors.joining(", ")) + ")";
    }

    public static String updateSqlStatement(String tableName, Set<String> attributes) {
        return "update " + tableName + " set " + attributes.stream().map(attribute -> attribute + " = ?")
                .collect(Collectors.joining(", ")) + " where id = ?";
    }

}
