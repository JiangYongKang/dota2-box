package com.jiangyongkang.active.record.core.provider;

import java.util.Map;

public class DynamicUpdateProvider extends DynamicSupportProvider {

    public String updateByAttrMap(Map<String, Object> attributeMap, Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append("update ");
        builder.append(super.tableName(clazz));
        builder.append(" set ");
        Object id = attributeMap.get("id");
        attributeMap.remove("id");
        attributeMap.keySet().forEach(key -> {
            builder.append(key);
            builder.append(" = ");
            builder.append(attributeMap.get(key));
            builder.append(", ");
        });
        builder.delete(builder.length() - 2, builder.length());
        if (id != null) {
            builder.append(" where id = ");
            builder.append(id);
        }
        return builder.toString();
    }

}
