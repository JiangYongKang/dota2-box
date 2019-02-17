package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.BeanUtils;
import com.jiangyongkang.active.record.toolkit.SQLUtils;
import com.jiangyongkang.active.record.toolkit.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: vincent
 * date: 2019-02-16 21:47
 * comment:
 */

public class InsertBuilder<E> extends AbstractBuilder<E> {

    public InsertBuilder(Class<E> modelClass) {
        builder.append("insert into ").append(SQLUtils.tableName(modelClass)).append(" ");
    }

    public InsertBuilder<E> auto(E model) {
        Map<String, Object> attributes = BeanUtils.beanToMap(model);
        return with(attributes);
    }

    public InsertBuilder<E> with(Map<String, Object> attributes) {
        builder.append("(");
        builder.append(
                attributes.keySet().stream().map(key -> StringUtils.underscore(key, true))
                        .collect(Collectors.joining(", "))
        );
        builder.append(")");
        builder.append(" values ");
        builder.append("(");
        builder.append(
                attributes.keySet().stream().map(index -> "?")
                        .collect(Collectors.joining(", "))
        );
        builder.append(")");
        args.addAll(Arrays.asList(attributes.values().toArray()));
        return this;
    }

    public int saveIt() {
        return template.update(builder.toString(), args.toArray());
    }
}
