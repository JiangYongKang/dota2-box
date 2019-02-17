package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.SQLUtils;

import java.util.Arrays;

/**
 * author: vincent
 * date: 2019-02-16 21:47
 * comment:
 */

public class UpdateBuilder<E> extends AbstractBuilder<E> {

    public UpdateBuilder(Class<E> modelClass) {
        builder.append("update ").append(SQLUtils.tableName(modelClass)).append(" set ");
    }

    public UpdateBuilder<E> set(String condition, Object... args) {
        builder.append(condition);
        this.args.addAll(Arrays.asList(args));
        return this;
    }

    public UpdateBuilder<E> where(String condition, Object... args) {
        if (builder.toString().contains("where")) {
            builder.append(" and ");
        } else {
            builder.append(" where ");
        }
        builder.append(condition);
        this.args.addAll(Arrays.asList(args));
        return this;
    }

    public int doIt() {
        return template.update(builder.toString(), args.toArray());
    }
}
