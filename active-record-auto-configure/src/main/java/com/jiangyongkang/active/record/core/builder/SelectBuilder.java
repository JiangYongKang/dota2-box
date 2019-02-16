package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * author: vincent
 * date: 2019-02-16 21:41
 * comment:
 */

public class SelectBuilder<E> {

    private Class<E> modelClass;
    private StringBuilder builder = new StringBuilder();
    private static final JdbcTemplate template = SpringContextUtil.findBean(JdbcTemplate.class);

    public SelectBuilder(Class<E> modelClass) {
        this.modelClass = modelClass;
    }

    public SelectBuilder<E> select(String... columns) {
        builder.append("select ");
        builder.append(String.join(", ", columns));
        builder.append(" form ");
        builder.append(modelClass.getSimpleName().toLowerCase());
        return this;
    }

    public SelectBuilder<E> where() {
        return this;
    }

    public E doIt() {
        String sql = builder.toString();
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(modelClass));
    }
}
