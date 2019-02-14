package com.jiangyongkang.active.record.core.provider;

import com.jiangyongkang.active.record.toolkit.StringUtils;

import java.util.Date;

public class DynamicSupportProvider {

    /**
     * 根据类型获取表名，默认表名为类名
     *
     * @param clazz 类型
     * @return 表名
     */
    public String tableName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    /**
     * 返回可执行的条件语句，如果 condition 为空，返回 true，反之返回原条件语句
     *
     * @param condition 条件语句
     * @return 可执行的条件语句
     */
    public String optionalCondition(String condition) {
        return StringUtils.isEmpty(condition) ? "true" : condition;
    }

    /**
     * 判断对象是否需要添加引号
     *
     * @param object 对象
     * @return 是否需要引号
     */
    public boolean needQuotes(Object object) {
        return object instanceof String || object instanceof Date || object instanceof Character;
    }

}
