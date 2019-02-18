package com.jiangyongkang.active.record.core.support;

import com.jiangyongkang.active.record.core.annotations.Table;
import com.jiangyongkang.active.record.core.properties.ActiveRecordProperties;
import com.jiangyongkang.active.record.toolkit.SpringContextUtils;
import com.jiangyongkang.active.record.toolkit.StringUtils;

/**
 * author: vincent
 * date: 2019-02-17 00:59
 * comment:
 */

public class ModelSupport {

    private static final ActiveRecordProperties properties = SpringContextUtils.findBean(ActiveRecordProperties.class);

    /**
     * 获取表名
     * @param moduleClass
     * @return
     */
    public static String tableName(Class<?> moduleClass) {
        StringBuilder builder = new StringBuilder();
        Table table = moduleClass.getAnnotation(Table.class);

        if (table != null && StringUtils.isNotEmpty(table.prefix())) {
            builder.append(table.prefix());
        } else {
            builder.append(properties.tableName().prefix());
        }

        if (table != null && StringUtils.isNotEmpty(table.name())) {
            builder.append(table.name());
        } else {
            String tableName = StringUtils.underscore(moduleClass.getSimpleName(), true);
            builder.append(tableName);
        }

        if (table != null && StringUtils.isNotEmpty(table.suffix())) {
            builder.append(table.suffix());
        } else {
            builder.append(properties.tableName().suffix());
        }
        return builder.toString();
    }

}
