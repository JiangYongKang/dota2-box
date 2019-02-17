package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.SQLUtils;
import com.jiangyongkang.active.record.toolkit.StringUtils;

import java.util.Arrays;

/**
 * author: vincent
 * date: 2019-02-16 21:47
 * comment:
 */

public class DeleteBuilder<E> extends AbstractBuilder<E> {

    public DeleteBuilder(Class<E> modelClass) {
        builder.append("delete from ").append(SQLUtils.tableName(modelClass)).append(" where true");
    }

    public DeleteBuilder<E> where(String condition, Object... args) {
        if (StringUtils.isNotEmpty(condition) && args.length > 0) {
            builder.append(" and ").append(condition);
            this.args.addAll(Arrays.asList(args));
        }
        return this;
    }

    public int doIt() {
        return template.update(builder.toString(), args.toArray());
    }
}
