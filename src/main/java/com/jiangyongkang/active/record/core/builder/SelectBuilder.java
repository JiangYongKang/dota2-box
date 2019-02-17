package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.SQLUtils;
import com.jiangyongkang.active.record.toolkit.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Arrays;
import java.util.List;

/**
 * author: vincent
 * date: 2019-02-16 21:41
 * comment:
 */

public class SelectBuilder<E> extends AbstractBuilder<E> {

    private BeanPropertyRowMapper<E> rowMapper;

    public SelectBuilder(Class<E> modelClass) {
        this.rowMapper = new BeanPropertyRowMapper<>(modelClass);
        this.builder.append("select * from ").append(SQLUtils.tableName(modelClass)).append(" where true");
    }

    public SelectBuilder<E> select(String... columns) {
        if (columns.length > 0) {
            int index = builder.indexOf("*");
            builder.replace(index, index + 1, String.join(", ", columns));
        }
        return this;
    }

    public SelectBuilder<E> where(String condition, Object... values) {
        if (StringUtils.isNotEmpty(condition) && values.length > 0) {
            builder.append(" and ").append(condition);
            args.addAll(Arrays.asList(values));
        }
        return this;
    }

    public SelectBuilder<E> limit(int count) {
        builder.append("limit ?");
        args.add(count);
        return this;
    }

    public SelectBuilder<E> orderBy(String... columns) {
        builder.append(" order by ").append(String.join(", ", columns)).append(" ");
        return this;
    }

    /**
     * 提取单条数据
     *
     * @return
     */
    public E fetchOne() {
        return template.queryForObject(builder.toString(), args.toArray(), rowMapper);
    }

    /**
     * 提取多条数据
     *
     * @return
     */
    public List<E> fetchMany() {
        return template.query(builder.toString(), args.toArray(), rowMapper);
    }

    /**
     * 提取整型结果
     *
     * @return
     */
    public Integer fetchToInt() {
        return template.queryForObject(builder.toString(), args.toArray(), Integer.class);
    }

    /**
     * 提取浮点型结果
     *
     * @return
     */
    public Double fetchToDouble() {
        return template.queryForObject(builder.toString(), args.toArray(), Double.class);
    }
}
