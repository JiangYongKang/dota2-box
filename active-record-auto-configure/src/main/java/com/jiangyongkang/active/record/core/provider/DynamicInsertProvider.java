package com.jiangyongkang.active.record.core.provider;

import com.jiangyongkang.active.record.toolkit.BeanUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DynamicInsertProvider extends DynamicSupportProvider {

    public String save(Object entity, Class<?> clazz) {
        Map<String, Object> attributes = BeanUtils.beanToMap(entity);
        return new SQL() {
            {
                INSERT_INTO(tableName(clazz));
                attributes.forEach((attributeName, attributeValue) -> {
                    if (attributeValue instanceof String)
                        attributeValue = "'" + attributeValue + "'";
                    VALUES(attributeName, String.valueOf(attributeValue));
                });
            }
        }.toString();
    }

}
