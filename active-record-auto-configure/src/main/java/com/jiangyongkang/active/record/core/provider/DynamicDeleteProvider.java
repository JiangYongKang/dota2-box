package com.jiangyongkang.active.record.core.provider;

import java.io.Serializable;

public class DynamicDeleteProvider extends DynamicSupportProvider {

    public String deleteById(Serializable id, Class<?> clazz) {
        return deleteBySQL("id = " + id, clazz);
    }

    public String deleteBySQL(String condition, Class<?> clazz) {
        return "delete from " + super.tableName(clazz) + " where " + condition;
    }

}
