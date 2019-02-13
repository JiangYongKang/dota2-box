package com.jiangyongkang.active.record.core.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DynamicUpdateProvider extends DynamicSupportProvider {

    public String update(Map<String, Object> attributes, Class<?> clazz) {
        Object id = attributes.remove("id");
        return new SQL() {
            {
                UPDATE(tableName(clazz));
                attributes.forEach((attributeName, attributeValue) -> {
                    if (attributeValue instanceof String)
                        attributeValue = "'" + attributeValue + "'";
                    SET(attributeName + " = " + attributeValue);
                });
                if (id != null)
                    WHERE("id = " + id);
            }
        }.toString();
    }

}
