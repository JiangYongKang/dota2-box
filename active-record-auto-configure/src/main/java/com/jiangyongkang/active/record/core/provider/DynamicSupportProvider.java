package com.jiangyongkang.active.record.core.provider;

import com.jiangyongkang.active.record.toolkit.StringUtils;

public class DynamicSupportProvider {

    public String tableName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    public String optionalCondition(String condition) {
        return StringUtils.isEmpty(condition) ? "true" : condition;
    }

}
